package com.xhz.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhz.entity.SmbmsUser;

public interface SmbmsUserService {
	/**
	 * 添加用户
	 * @param smbmsUser
	 * @return
	 */
	int addSmbmsUser(SmbmsUser smbmsUser);
	/**
	 * 更新用户
	 * @param smbmsUser
	 * @return
	 */
	int updateSmbmsUser(SmbmsUser smbmsUser);
	/**
	 * 删除用户信息
	 * @param smbmsUser
	 * @return 返回0或1
	 */
	int delSmbmsUser(SmbmsUser smbmsUser);
	/**
	 * 根据用户名查询用户
	 * @param smbmsUser
	 * @return 返回对象或null
	 */
	SmbmsUser findSmbmsUserByCode(SmbmsUser smbmsUser);
	/**
	 * 登录
	 * @param smbmsUser
	 * @return
	 */
	SmbmsUser findSmbmsUser(SmbmsUser smbmsUser);
	/**
	 * 查询所有用户
	 * @return
	 */
	List<SmbmsUser> findAllUser(String userName,Integer userRole);
	List<SmbmsUser> findSmbmsUsersByList(String userName,Integer userRole,int pageIndex,int pageSize);
	/**
	 * 修改密码
	 * @param smbmsUser
	 * @param newPassword
	 * @return
	 */
	int updatePassword(String userCode,String userpassword,String newPassword);
}
