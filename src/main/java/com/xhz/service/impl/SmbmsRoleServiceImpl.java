package com.xhz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.dao.SmbmsRoleDao;
import com.xhz.entity.SmbmsRole;
import com.xhz.service.SmbmsRoleService;
@Service
public class SmbmsRoleServiceImpl implements SmbmsRoleService {
	@Autowired
	private SmbmsRoleDao smbmsRoleDao;
	public List<SmbmsRole> finAllRole() {
		return smbmsRoleDao.finAllRole();
	}
	public SmbmsRole findById(Integer id) {
		return smbmsRoleDao.findById(id);
	}
	public SmbmsRole findByRoleName(String roleName) {
		return smbmsRoleDao.findByRoleName(roleName);
	}
	public int addRole(SmbmsRole smbmsRole) {
		return smbmsRoleDao.addRole(smbmsRole);
	}
	public int updateRole(SmbmsRole smbmsRole) {
		return smbmsRoleDao.updateRole(smbmsRole);
	}
	public int delRole(Integer id) {
		return smbmsRoleDao.delRole(id);
	}
	

}
