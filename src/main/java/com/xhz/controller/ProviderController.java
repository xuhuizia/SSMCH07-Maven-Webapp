package com.xhz.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.xhz.entity.SmbmsProvider;
import com.xhz.entity.SmbmsUser;
import com.xhz.service.SmbmsProviderService;

@Controller
public class ProviderController {
	@Autowired
	private SmbmsProviderService smbmsProviderService;
	// 页面大小
	private int pageSize = 5;

	// 显示供应商
	@RequestMapping("provider.do")
	public String findProvider(String queryProCode, String queryProName,
			@RequestParam(defaultValue = "1") int pageIndex,
			Map<String, Object> map) {
		// 查询总记录数
		List<SmbmsProvider> findAllProvider = smbmsProviderService
				.findAllProvider(queryProCode, queryProName);

		int totalCount = findAllProvider.size();
		List<SmbmsProvider> findSmbmsProviderByList = smbmsProviderService
				.findSmbmsProviderByList(queryProCode, queryProName, pageIndex,
						pageSize);
		// 总页数
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		map.put("currentPageNo", pageIndex);// 当前页
		map.put("totalCount", totalCount);// 总记录数
		map.put("totalPageCount", totalPageCount);// 页面总数
		map.put("providerList", findSmbmsProviderByList);
		map.put("queryProCode", queryProCode);
		map.put("queryProName", queryProName);
		return "providerlist";
	}

	/**
	 * 跳转到添加供应商界面
	 * 
	 * @param smbmsProvider
	 * @return
	 */
	@RequestMapping("goProvideradd.do")
	public String goProviderAdd() {
		return "provideradd";
	}

	@RequestMapping("provideradd.do")
	public String addProvider(
			@ModelAttribute("smbmsProvider") @Valid SmbmsProvider smbmsProvider,
			BindingResult result,
			@RequestParam("photos") MultipartFile[] photos,
			HttpServletRequest req, HttpSession session) {
		if (!result.hasErrors()) {
			String path = req.getSession().getServletContext()
					.getRealPath("photos");
			List<String> errors = new ArrayList<String>();
			List<String> filenames = new ArrayList<String>();
			// 1.检测
			for (MultipartFile photo : photos) {
				String err = getErrInfo(photo);
				// 2.上传
				if (err == null) {
					String fileName = upload(photo, path);
					filenames.add(fileName);
				} else {
					errors.add(err);
				}
			}
			SmbmsUser attribute = (SmbmsUser) session.getAttribute("smbmsUser");
			smbmsProvider.setCreatedBy(attribute.getId());
			smbmsProvider.setCreationDate(new Timestamp(System
					.currentTimeMillis()));
			if (filenames.size() > 0) {
				smbmsProvider.setZhiZhao("photos/" 
						+ filenames.get(0));
				smbmsProvider.setDaiMa("photos/"
						+ filenames.get(1)); 
				if (smbmsProviderService.addSmbmsProvider(smbmsProvider) > 0) {
					return "redirect:provider.do";
				}
			}
			req.setAttribute("error", errors);
			req.setAttribute("filenames", filenames);
		}
		return "provideradd";
	}

	/**
	 * 
	 * @param photo
	 *            图片
	 * @param path
	 * @return null or 图片地址名
	 */
	public String upload(MultipartFile photo, String path) {
		String fileName = photo.getOriginalFilename();
		// 拼接
		File dest = new File(path, fileName);
		try {
			photo.transferTo(dest);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上传
	 * 
	 * @param photo
	 * @return
	 */
	public String getErrInfo(MultipartFile photo) {
		// 得到后缀名
		String fileName = photo.getOriginalFilename();
		String suf = FilenameUtils.getExtension(fileName);
		String err = null;
		if (photo.isEmpty()) {
			err = String.format("%s：上传文件不能为空", fileName);
		} else if (photo.getSize() > 500000) {
			err = String.format("%s：上传文件大小不能超过500KB", fileName);
		} else if (!formats.contains(suf.toLowerCase())) {
			err = String.format("%s：上传文件格式不正确，支持的格式：%s", fileName, formats);
		}
		return err;
	}

	// 支持的格式
	static List<String> formats = new ArrayList<String>();
	static {
		formats.add("png");
		formats.add("jpg");
		formats.add("jpeg");
		formats.add("pneg");
	}
	/**
	 * 查看
	 * @param proid
	 * @param session
	 * @return
	 */
	@RequestMapping("viewProvider.do")
	public String viewProvider(Integer proid, HttpSession session) {
		SmbmsProvider findProviderById = smbmsProviderService
				.findProviderById(proid);
		session.setAttribute("provider", findProviderById);
		return "providerview";
	}
	/**
	 * 跳转到供应商修改界面
	 * @param smbmsProvider
	 * @param proid
	 * @param session
	 * @return
	 */
	@RequestMapping("goUpdateProvider.do")
	public String goUpdateProvider(
			@ModelAttribute("smbmsProvider") SmbmsProvider smbmsProvider,
			Integer proid, HttpSession session) {
		SmbmsProvider findProviderById = smbmsProviderService
				.findProviderById(proid);
		session.setAttribute("provider", findProviderById);
		return "providermodify";
	}
	/**
	 * 更新
	 * @param smbmsProvider
	 * @param session
	 * @return
	 */
	@RequestMapping("updateProvider.do")
	public String updateProvider(SmbmsProvider smbmsProvider,
			HttpSession session) {
		SmbmsUser user = (SmbmsUser) session.getAttribute("smbmsUser");
		SmbmsProvider provider = (SmbmsProvider) session
				.getAttribute("provider");
		smbmsProvider.setId(provider.getId());
		smbmsProvider.setModifyBy(user.getId());
		smbmsProvider.setModifyDate(new Timestamp(System.currentTimeMillis()));
		smbmsProviderService.updateSmbmsProvider(smbmsProvider);
		return "redirect:provider.do";
	}
	/**
	 * 删除供应商
	 * @param proid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("providerDel.do")
	public String providerDel(@RequestParam Integer proid) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int findCountById = smbmsProviderService.findCountById(proid);
		if (findCountById>0) {
			resultMap.put("delResult", findCountById);
		} else {
			int delSmbmsUser = smbmsProviderService.delSmbmsProvider(proid);
			if (delSmbmsUser>0) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
}
