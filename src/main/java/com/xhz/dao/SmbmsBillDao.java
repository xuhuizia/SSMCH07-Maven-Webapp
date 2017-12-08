package com.xhz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhz.entity.SmbmsBill;

public interface SmbmsBillDao {
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
	List<SmbmsBill> findSmbmsBillsByList(@Param("productName") String productName,@Param("providerId")Integer providerId,@Param("isPayment")Integer isPayment,@Param("start")int start,@Param("count")int count);
	
	int findAllBill(@Param("productName") String productName,@Param("providerId")Integer providerId,@Param("isPayment")Integer isPayment);
}
