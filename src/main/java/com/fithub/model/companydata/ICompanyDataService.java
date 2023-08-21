package com.fithub.model.companydata;

import java.util.List;

public interface ICompanyDataService {

	// 查詢全部
	List<CompanyData> findAll();

	// 新增單筆
	CompanyData insert(CompanyData companyData);

	// 修改單筆
	void updateById(CompanyData companyData);

	// 刪除單筆
	void deleteById(Integer id);

	// 確認id存在
	Boolean findById(Integer id);

}