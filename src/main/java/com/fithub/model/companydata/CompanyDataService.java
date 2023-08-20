package com.fithub.model.companydata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyDataService implements ICompanyDataService {

	@Autowired
	private CompanyDataRespository cDataRespository;

	// 查詢全部
	@Override
	public List<CompanyData> findAll() {
		List<CompanyData> result = cDataRespository.findAll();
		return result;
	}

	// 新增單筆
	@Override
	public CompanyData insert(CompanyData companyData) {
		CompanyData result = cDataRespository.save(companyData);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(CompanyData companyData) {
		Boolean result = findById(companyData.getCompanyid());
		if (result) {
			cDataRespository.saveAndFlush(companyData);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = cDataRespository.existsById(id);
		if (result) {
			cDataRespository.deleteById(id);
		}
	}

	// 確認id存在
	@Override
	public Boolean findById(Integer id) {
		Boolean result = cDataRespository.existsById(id);
		return result;
	}
}
