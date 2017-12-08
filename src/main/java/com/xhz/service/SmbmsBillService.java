package com.xhz.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhz.entity.SmbmsBill;

public interface SmbmsBillService {
	/**
	 * 添加订单
	 * @param SmbmsBill
	 * @return
	 */
	int addSmbmsBill(SmbmsBill smbmsBill);
	/**
	 * 更新订单
	 * @param SmbmsBill
	 * @return
	 */
	int updateSmbmsBill(SmbmsBill smbmsBill);
	/**
	 * 删除订单
	 * @param SmbmsBill
	 * @return
	 */
	int delSmbmsBill(Integer id);
	/**
	 * 查询订单
	 * @param SmbmsBill
	 * @return
	 */
	SmbmsBill findSmbmsBillById(Integer id);
	/**
	 * 分页查询
	 * @param start
	 * @param count
	 * @return
	 */
	List<SmbmsBill> findSmbmsBillsByList(String productName,Integer providerId,Integer isPayment,int start,int count);
	
	int findAllBill(String productName,Integer providerId,Integer isPayment);
}
