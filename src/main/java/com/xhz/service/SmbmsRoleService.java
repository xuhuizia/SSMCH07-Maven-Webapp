package com.xhz.service;

import java.util.List;

import com.xhz.entity.SmbmsRole;

public interface SmbmsRoleService {
	/**
	 * 查询所有职位
	 * @return
	 */
	List<SmbmsRole> finAllRole();
	/**
	 * 根据id查询职位
	 * @param roleId
	 * @return
	 */
	SmbmsRole findById(Integer id);
	/**
	 * 根据编码查询
	 * @param roleName
	 * @return
	 */
	SmbmsRole findByRoleName(String roleName);
	/**
	 * 添加角色
	 */
	int addRole(SmbmsRole smbmsRole);
	/**
	 * 修改角色
	 * @param smbmsRole
	 * @return
	 */
	int updateRole(SmbmsRole smbmsRole);
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	int delRole(Integer id);
}
