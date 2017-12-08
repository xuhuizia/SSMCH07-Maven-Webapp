package com.xhz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.xhz.service.SmbmsRoleService;

@Controller
public class RoleController {
	@Autowired
	private SmbmsRoleService smbmsRoleService;
	/**
	 * 查询所有的用户角色
	 * @return
	 */
	@RequestMapping("geteRole.do")
	@ResponseBody
	public String  getRole(){
		return JSONArray.toJSONString(smbmsRoleService.finAllRole());
	}
	
	
}