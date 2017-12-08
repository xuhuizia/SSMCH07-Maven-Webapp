package com.xhz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhz.entity.SmbmsProvider;

public interface SmbmsProviderDao {
	/**
	 * 添加供应商
	 * @param id
	 * @return
	 */
	int addSmbmsProvider(SmbmsProvider smbmsProvider);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delSmbmsProvider(Integer id);
	/**
	 * 修改
	 * @param provider
	 * @return
	 */
	int updateSmbmsProvider(SmbmsProvider provider);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	SmbmsProvider findProviderById(Integer id);
	/**
	 * 查询所有
	 * @return
	 */
	List<SmbmsProvider> findProvider();
	/**
	 * 分页查询
	 * @param start
	 * @param count
	 * @return
	 */
	List<SmbmsProvider> findSmbmsProviderByList(@Param("proCode") String proCode,@Param("proName")String proName,@Param("start")int start,@Param("count")int count);
	
	List<SmbmsProvider> findAllProvider(@Param("proCode") String proCode,@Param("proName")String proName);
	/**
	 * 查询该供应商下的订单数量
	 * @param id
	 * @return
	 */
	int findCountById(Integer id);
}
