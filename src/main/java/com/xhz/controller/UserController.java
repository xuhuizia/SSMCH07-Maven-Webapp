package com.xhz.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import com.xhz.entity.SmbmsRole;
import com.xhz.entity.SmbmsUser;
import com.xhz.service.SmbmsRoleService;
import com.xhz.service.SmbmsUserService;

@Controller
public class UserController {
	@Autowired
	private SmbmsUserService smbmsUserService;
	@Autowired
	private SmbmsRoleService smbmsRoleService;

	/**
	 * 登录
	 * 
	 * @param userCode
	 *            登录名
	 * @param userPassword
	 *            密码
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST, params = {
			"userCode", "userPassword" })
	public String login(String userCode, String userPassword,
			HttpSession session) {
		SmbmsUser smbmsUser = new SmbmsUser(userCode, userPassword);
		try {
			SmbmsUser findSmbmsUser = smbmsUserService.findSmbmsUser(smbmsUser);
			if (findSmbmsUser != null) {
				session.setAttribute("smbmsUser", findSmbmsUser);
				return "frame";
			} else {
				session.setAttribute("message", "账号或密码错误");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param queryname
	 *            用户名
	 * @param queryUserRole
	 *            用户职位
	 * @param page
	 *            页面大小
	 * @param session
	 * @return
	 */
	// 页面大小
	private int pageSize = 5;

	@RequestMapping(value = "/info.do", produces = "text/plain;charset=utf-8")
	public String findAllUser(String queryname, Integer queryUserRole,
			@RequestParam(defaultValue = "1") int pageIndex,
			Map<String, Object> map) {
		List<SmbmsUser> findSmbmsUsersByList = smbmsUserService
				.findSmbmsUsersByList(queryname, queryUserRole, pageIndex,
						pageSize);
		List<SmbmsUser> findAllUser = smbmsUserService.findAllUser(queryname,
				queryUserRole);
		// 总记录数
		int totalCount = findAllUser.size();
		// 总页数
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize + 1;
		// 查询所有职位
		List<SmbmsRole> finAllRole = smbmsRoleService.finAllRole();
		map.put("roleList", finAllRole);
		map.put("currentPageNo", pageIndex);// 当前页
		map.put("totalCount", totalCount);// 总记录数
		map.put("totalPageCount", totalPageCount);// 页面总数
		map.put("user", findSmbmsUsersByList);
		map.put("queryname", queryname);
		map.put("queryUserRole", queryUserRole);
		return "userlist";
	}

	/**
	 * 跳转到添加用户界面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("goAdd.do")
	public String goAdd(@ModelAttribute("smbmsUser") SmbmsUser smbmsUser) {
		return "useradd";
	}

	/**
	 * 判断用户编码是否存在
	 */
	@ResponseBody
	@RequestMapping("getCode.do")
	public String getCode(@RequestParam String userCode) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userCode)) {
			resultMap.put("userCode", "exist");
		} else {
			SmbmsUser smbmsUser = new SmbmsUser();
			smbmsUser.setUserCode(userCode);
			SmbmsUser user = smbmsUserService.findSmbmsUserByCode(smbmsUser);
			if (user != null) {
				resultMap.put("userCode", "exist");
			} else {
				resultMap.put("userCode", "noexist");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@RequestMapping("/userAdd.do")
	public String userAdd(@Valid SmbmsUser smbmsUser, HttpSession session) {
		SmbmsUser attribute = (SmbmsUser) session.getAttribute("smbmsUser");
		smbmsUser.setCreatedBy(attribute.getId());
		smbmsUser.setCreationDate(new Timestamp(System.currentTimeMillis()));
		smbmsUserService.addSmbmsUser(smbmsUser);
		return "redirect:info.do";
	}

	/**
	 * 查看用户信息
	 * 
	 * @param uid
	 * @param session
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("goUserById.do") public String goUserById(@RequestParam
	 * Integer id) { SmbmsUser smbmsUser = new SmbmsUser();
	 * smbmsUser.setId((long)id); SmbmsUser findSmbmsUser = smbmsUserService
	 * .findSmbmsUserByCode(smbmsUser); return
	 * JSONArray.toJSONString(findSmbmsUser); }
	 */
	@ResponseBody
	@RequestMapping("goUserById.do")
	public Object goUserById(@RequestParam Integer id) {
		SmbmsUser smbmsUser = new SmbmsUser();
		smbmsUser.setId((long) id);
		return smbmsUserService.findSmbmsUserByCode(smbmsUser);
	}
	/**
	 * 查看用户信息
	 * 
	 * @param uid
	 * @param session
	 * @return
	 */
	@RequestMapping("goUserView.do")
	public String goUserView(String uid, HttpSession session) {
		SmbmsUser smbmsUser = new SmbmsUser();
		smbmsUser.setId(Long.parseLong(uid));
		SmbmsUser findSmbmsUser = smbmsUserService
				.findSmbmsUserByCode(smbmsUser);
		session.setAttribute("user", findSmbmsUser);
		return "userview";
	}

	/**
	 * 跳转到用户修改界面
	 * 
	 * @param smbmsUser
	 * @param uid
	 * @param session
	 * @return
	 */
	@RequestMapping("goUserUpdate.do")
	public String goUserUpdate(
			@ModelAttribute("smbmsUser") SmbmsUser smbmsUser, String uid,
			HttpSession session) {
		smbmsUser.setId(Long.parseLong(uid));
		SmbmsUser findSmbmsUser = smbmsUserService
				.findSmbmsUserByCode(smbmsUser);
		session.setAttribute("user", findSmbmsUser);
		return "usermodify";
	}

	/**
	 * 修改用户信息
	 * 
	 * @param smbmsUser
	 * @param session
	 * @return
	 */
	@RequestMapping("userUpdate.do")
	public String userUpdate(SmbmsUser smbmsUser, HttpSession session) {
		SmbmsUser attribute = (SmbmsUser) session.getAttribute("smbmsUser");
		smbmsUser.setModifyBy(attribute.getId());
		smbmsUser.setModifyDate(new Timestamp(System.currentTimeMillis()));
		smbmsUserService.updateSmbmsUser(smbmsUser);
		return "redirect:info.do";
	}

	/**
	 * 删除
	 * 
	 * @param uid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("userDel.do")
	public String userDel(@RequestParam String uid) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		SmbmsUser smbmsUser = new SmbmsUser();
		smbmsUser.setId(Long.parseLong(uid));
		if (smbmsUserService.delSmbmsUser(smbmsUser) > 0) {
			resultMap.put("delResult", "true");
		} else {
			resultMap.put("delResult", "false");
		}
		return JSONArray.toJSONString(resultMap);
	}
	/**
	 * 判断旧密码是否输入正确
	 * @param oldpassword 旧密码
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatePwd.do")
	public String updatePwd(@RequestParam String oldpassword,HttpSession session){
		//获得登录的session
		SmbmsUser user = (SmbmsUser) session.getAttribute("smbmsUser");
		HashMap<String , String> resultMap = new HashMap<String, String>();
		//拿到用户编码
		String userCode = user.getUserCode();
		//判断用户是否输入旧密码
		if (oldpassword!=null||oldpassword!="") {
			SmbmsUser smbmsUser = new SmbmsUser(userCode, oldpassword);
			//判断是否存在
			SmbmsUser findSmbmsUser = smbmsUserService.findSmbmsUser(smbmsUser);
			if (findSmbmsUser!=null) {
				resultMap.put("result", "true");
			} else{
				resultMap.put("result", "false");
			}
		}else {
			resultMap.put("result", "error");
		}
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * 跳转到修改密码界面
	 * 
	 * @return
	 */
	@RequestMapping("goUpdatePassword.do")
	public String goUpdatePassword() {
		return "pwdmodify";
	}

	/**
	 * 修改密码
	 * 
	 * @param newpassword
	 * @param session
	 * @return
	 */
	@RequestMapping("updatePassword.do")
	public String updatePassword(String newpassword, HttpSession session) {
		SmbmsUser smbmsUser = (SmbmsUser) session.getAttribute("smbmsUser");
		String userCode = smbmsUser.getUserCode();
		String userpassword = smbmsUser.getUserPassword();
		try {
			smbmsUserService
					.updatePassword(userCode, userpassword, newpassword);
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", "修改密码失败");
			return "pwdmodify";
		}
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String exit(HttpSession session) {
		session.removeAttribute("smbmsUser");
		return "login";
	}

}
