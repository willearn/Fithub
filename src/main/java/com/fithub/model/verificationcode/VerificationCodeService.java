package com.fithub.model.verificationcode;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService implements IVerificationCodeService{
	
	@Autowired
	private VerificationCodeRepository verificationcodeRepo;

	// 查詢全部
	@Override
	public List<VerificationCode> findAll() {
		List<VerificationCode> result = verificationcodeRepo.findAll();
		return result;
	}

	// 新增單筆
	@Override
	public VerificationCode insert(VerificationCode verificationcode) {
		VerificationCode result = verificationcodeRepo.save(verificationcode);
		return result;
	}

	// 修改單筆
	@Override
	public void updateByEmail(String email) {
		Optional<VerificationCode> result = verificationcodeRepo.findByEmail(email);
		 return;
		}

	// 刪除單筆
	@Override
	public void deleteByEmail(String email) {
		Boolean result = verificationcodeRepo.existsByEmail(email);

		if (result) {
			verificationcodeRepo.deleteByEmail(email);
		}
	}

	// 確認id存在
//	@Override
//	public Boolean findById(Integer id) {
//		Boolean result = memberRepo.existsById(id);
//		return result;
//	}
	
	@Override
	public VerificationCode findByEmail(String email) {
		Optional<VerificationCode> resultBean = verificationcodeRepo.findByEmail(email);
		if(resultBean.isPresent()) {
			return resultBean.get();
		}
		return null;
	}
}
