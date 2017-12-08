package com.xhz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhz.dao.SmbmsProviderDao;
import com.xhz.entity.SmbmsProvider;
import com.xhz.service.SmbmsProviderService;
@Service
public class SmbmsProviderServiceImpl implements SmbmsProviderService {

	@Autowired
	private SmbmsProviderDao smbmsProviderDao;
	public int addSmbmsProvider(SmbmsProvider smbmsProvider) {
		return smbmsProviderDao.addSmbmsProvider(smbmsProvider);
	}

	public int delSmbmsProvider(Integer id) {
		return smbmsProviderDao.delSmbmsProvider(id);
	}

	public int updateSmbmsProvider(SmbmsProvider provider) {
		return smbmsProviderDao.updateSmbmsProvider(provider);
	}

	public SmbmsProvider findProviderById(Integer id) {
		return smbmsProviderDao.findProviderById(id);
	}

	public List<SmbmsProvider> findSmbmsProviderByList(String proCode,
			String proName, int pageIndex, int pageSize) {
		return smbmsProviderDao.findSmbmsProviderByList(proCode, proName, (pageIndex-1)*pageSize, pageSize);
	}

	public List<SmbmsProvider> findAllProvider(String proCode, String proName) {
		return smbmsProviderDao.findAllProvider(proCode, proName);
	}

	public List<SmbmsProvider> findProvider() {
		return smbmsProviderDao.findProvider();
	}

	public int findCountById(Integer id) {
		return smbmsProviderDao.findCountById(id);
	}

	

}
