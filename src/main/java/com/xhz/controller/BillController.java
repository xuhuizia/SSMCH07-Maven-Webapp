package com.xhz.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xhz.entity.SmbmsBill;
import com.xhz.entity.SmbmsProvider;
import com.xhz.entity.SmbmsUser;
import com.xhz.service.SmbmsBillService;
import com.xhz.service.SmbmsProviderService;

@Controller
public class BillController {
	@Autowired
	private SmbmsBillService smbmsBillService;
	@Autowired
	private SmbmsProviderService smbmsProviderService;
	// 每页大小
	private int pageSize = 5;

	// 查询分页   
	@RequestMapping(value = "/bill.do", produces = "text/plain;charset=utf-8")
	public String findBill(String queryProductName, Integer queryProviderId,
			Integer queryIsPayment,
			@RequestParam(defaultValue = "1") int pageIndex,
			Map<String, Object> map) {
		// 查询所有供应商
		List<SmbmsProvider> findProvider = smbmsProviderService.findProvider();
		// 总数
		int totalCount = smbmsBillService.findAllBill(queryProductName,
				queryProviderId, queryIsPayment);
		List<SmbmsBill> findSmbmsBillsByList = smbmsBillService
				.findSmbmsBillsByList(queryProductName, queryProviderId,
						queryIsPayment, pageIndex, pageSize);
		// 总页数
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		map.put("currentPageNo", pageIndex);// 当前页
		map.put("totalCount", totalCount);// 总记录数
		map.put("totalPageCount", totalPageCount);// 页面总数
		map.put("billList", findSmbmsBillsByList);
		map.put("providerList", findProvider);
		map.put("queryProductName", queryProductName);
		map.put("queryProviderId", queryProviderId);
		map.put("queryIsPayment", queryIsPayment);
		return "billlist";
	}

	/**
	 * 查询
	 */
	@RequestMapping("viewBill.do")
	public String viewBill(Integer billid, HttpSession session) {
		SmbmsBill findSmbmsBillById = smbmsBillService
				.findSmbmsBillById(billid);
		session.setAttribute("bill", findSmbmsBillById);
		return "billview";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param smbmsBill
	 * @param billid
	 * @param session
	 * @return
	 */
	@RequestMapping("goUpdateBill.do")
	public String goUpdateBill(
			@ModelAttribute("smbmsBill") SmbmsBill smbmsBill, Integer billid,
			HttpSession session) {
		// 查询所有供应商
		List<SmbmsProvider> findProvider = smbmsProviderService.findProvider();
		session.setAttribute("providerList", findProvider);
		SmbmsBill findSmbmsBillById = smbmsBillService
				.findSmbmsBillById(billid);
		session.setAttribute("bill", findSmbmsBillById);
		return "billmodify";
	}

	/**
	 * 修改
	 * 
	 * @param smbmsBill
	 * @param session
	 * @return
	 */
	@RequestMapping("updateBill.do")
	public String updateBill(SmbmsBill smbmsBill, HttpSession session) {
		SmbmsUser user = (SmbmsUser) session.getAttribute("smbmsUser");
		smbmsBill.setModifyBy(user.getId());
		smbmsBill.setModifyDate(new Timestamp(System.currentTimeMillis()));
		smbmsBillService.updateSmbmsBill(smbmsBill);
		return "redirect:bill.do";
	}

	/**
	 * 删除
	 * 
	 * @param billid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delBill.do")
	public String delBill(@RequestParam Integer billid) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		int delSmbmsBill = smbmsBillService.delSmbmsBill(billid);
		if (delSmbmsBill>0) {

			
			resultMap.put("delResult", "true");
		} else {
			resultMap.put("delResult", "false");
		}
		return JSONArray.toJSONString(resultMap);
	}
	/**
	 * 使用json向页面传输数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doAddBill.do")
	public String doAddBill() {
		return JSONArray.toJSONString(smbmsProviderService.findProvider());
	}
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping("goAddBill.do")
	public String goAddBill(@ModelAttribute("smbmsBill")  SmbmsBill smbmsBill){
		return "billadd";
	}
	@RequestMapping("addBill.do")
	public String addBill(SmbmsBill smbmsBill,HttpSession session){
		SmbmsUser user = (SmbmsUser) session.getAttribute("smbmsUser");
		smbmsBill.setCreatedBy(user.getId());
		smbmsBill.setCreationDate(new Timestamp(System.currentTimeMillis()));
        		smbmsBillService.addSmbmsBill(smbmsBill);
		return "redirect:bill.do";
	}
	
}
