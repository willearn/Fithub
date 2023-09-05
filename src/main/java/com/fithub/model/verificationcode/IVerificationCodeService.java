package com.fithub.model.verificationcode;

import java.util.List;

public interface IVerificationCodeService {
	

	// 查詢全部
//	List<VerificationCode> findAll();

	// 新增單筆
	public VerificationCode insert(VerificationCode vBean);
	
	//確認驗證碼
	public boolean checkVerificationCode(VerificationCode vBean);

	// 修改單筆
//	void updateByEmail(String email);

	// 刪除單筆
//	void deleteByEmail(String email);

	// 確認id存在
//	Boolean findById(Integer verificationcode);
	
//	public VerificationCode findByEmail(String email);

}
