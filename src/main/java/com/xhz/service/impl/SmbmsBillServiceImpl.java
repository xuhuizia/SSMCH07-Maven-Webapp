package com.xhz.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.dao.SmbmsBillDao;
import com.xhz.entity.SmbmsBill;
import com.xhz.service.SmbmsBillService;
@Service
public class SmbmsBillServiceImpl implements SmbmsBillService {
	@Autowired
	private SmbmsBillDao smbmsBillDao;
	public int addSmbmsBill(SmbmsBill smbmsBill) {
		return smbmsBillDao.addSmbmsBill(smbmsBill);
	}

	public int updateSmbmsBill(SmbmsBill smbmsBill) {
		return smbmsBillDao.updateSmbmsBill(smbmsBill);
	}

	public int delSmbmsBill(Integer id) {
		return smbmsBillDao.delSmbmsBill(id);
	}

	public SmbmsBill findSmbmsBillById(Integer id) {
		return smbmsBillDao.findSmbmsBillById(id);
	}

	public List<SmbmsBill> findSmbmsBillsByList(
			String productName,
			Integer providerId,Integer isPayment,int start,
			int count) {
		return smbmsBillDao.findSmbmsBillsByList(productName, providerId,isPayment, (start-1)*count, count);
	}

	public int findAllBill(String productName,
			Integer providerId,Integer isPayment) {
		// TODO Auto-generated method stub
		return smbmsBillDao.findAllBill(productName, providerId,isPayment);
	}

}
