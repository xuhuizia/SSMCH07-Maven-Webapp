package com.xhz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhz.entity.SmbmsUser;

public interface SmbmsUserDao {
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
	 * 删除用户
	 * @param smbmsUser
	 * @return
	 */
	int delSmbmsUser(SmbmsUser smbmsUser);
	/**
	 * 查询用户
	 * @param smbmsUser
	 * @return
	 */
	SmbmsUser findSmbmsUser(SmbmsUser smbmsUser);
	/**
	 * 查询用户
	 * @param smbmsUser
	 * @return
	 */
	SmbmsUser findSmbmsUserByCode(SmbmsUser smbmsUser);
	/**
	 * 分页查询
	 * @param start
	 * @param count
	 * @return
	 */
	List<SmbmsUser> findSmbmsUsersByList(@Param("userName") String userName,@Param("userRole")Integer userRole,@Param("start")int start,@Param("count")int count);
	
	List<SmbmsUser> findAllUser(@Param("userName") String userName,@Param("userRole")Integer userRole);
	/**
	 * 查询用户总数
	 * @return
	 */
	int findAll();
	
	int updatePassword(@Param("userCode") String userCode,@Param("userPassword") String userPassword,@Param("newPassword") String newPassword);
}
