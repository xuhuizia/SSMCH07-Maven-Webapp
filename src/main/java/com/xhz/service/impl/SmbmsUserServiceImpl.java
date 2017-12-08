package com.xhz.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xhz.dao.SmbmsUserDao;
import com.xhz.entity.SmbmsUser;
import com.xhz.service.SmbmsUserService;
@Service("smbmsUserService")
public class SmbmsUserServiceImpl implements SmbmsUserService {
	@Autowired
	private SmbmsUserDao smbmsUserDao;

	public SmbmsUser findSmbmsUser(SmbmsUser smbmsUser) {
		try {
			return smbmsUserDao.findSmbmsUser(smbmsUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SmbmsUser> findAllUser(String userName,Integer userRole) {
		try {
			return smbmsUserDao.findAllUser(userName,userRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int delSmbmsUser(SmbmsUser smbmsUser) {
		try {
			int delSmbmsUser = smbmsUserDao.delSmbmsUser(smbmsUser);
			return delSmbmsUser;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public SmbmsUser findSmbmsUserByCode(SmbmsUser smbmsUser) {
		try {
			SmbmsUser findSmbmsUserByCode = smbmsUserDao
					.findSmbmsUserByCode(smbmsUser);
			return findSmbmsUserByCode;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public SmbmsUserDao getSmbmsUserDao() {
		return smbmsUserDao;
	}

	public void setSmbmsUserDao(SmbmsUserDao smbmsUserDao) {
		this.smbmsUserDao = smbmsUserDao;
	}

	public List<SmbmsUser> findSmbmsUsersByList(String userName,Integer userRole,int pageIndex,int pageSize) {
		return smbmsUserDao.findSmbmsUsersByList(userName, userRole, (pageIndex-1)*pageSize, pageSize);
	}

	public int addSmbmsUser(SmbmsUser smbmsUser) {
		return smbmsUserDao.addSmbmsUser(smbmsUser);
	}

	public int updateSmbmsUser(SmbmsUser smbmsUser) {
		return smbmsUserDao.updateSmbmsUser(smbmsUser);
	}

	public int updatePassword(String userCode,String userpassword, String newPassword) {
		return smbmsUserDao.updatePassword(userCode, userpassword, newPassword);
	}


}
