package com.xhz.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhz.entity.SmbmsProvider;

public interface SmbmsProviderService {
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
	List<SmbmsProvider> findSmbmsProviderByList(String proCode,String proName,int pageIndex,int pageSize);
	
	List<SmbmsProvider> findAllProvider(String proCode,String proName);
	int findCountById(Integer id);
}
