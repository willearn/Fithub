package com.fithub.model.verificationcode;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService implements IVerificationCodeService {

	@Autowired
	private VerificationCodeRepository vRepo;

	// 新增單筆
	@Override
	public VerificationCode insert(VerificationCode vBean) {
		try {
			// 六位隨機數
			Random random = new Random();
			String code = Integer.toString(100000 + random.nextInt(900000));

			// 取得當前日期時間
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedDate = dateFormat.format(currentDate);

			vBean.setVerificationcode(code);
			vBean.setCodeGeneratedate(formattedDate);

			VerificationCode resultBean = vRepo.save(vBean);
			return resultBean;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public boolean checkVerificationCode(VerificationCode vBean) {
		try {
			VerificationCode resultBean = vRepo.findByEmail(vBean.getEmail());

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			LocalDateTime currentTime = LocalDateTime.now();
			LocalDateTime resultTime = LocalDateTime.parse(resultBean.getCodeGeneratedate(), formatter);

			Duration duration = Duration.between(resultTime, currentTime);
			
			long seconds = duration.getSeconds();
			
			if(seconds > 300) {
				return false;
			}
			
			if(vBean.getVerificationcode().equals(resultBean.getVerificationcode())) {
				System.out.println("Code TRUE");
				return true;
			}
			System.out.println("Code FALSE");
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

//	// 查詢全部
//	@Override
//	public List<VerificationCode> findAll() {
//		List<VerificationCode> result = verificationcodeRepo.findAll();
//		return result;
//	}

//	// 修改單筆
//	@Override
//	public void updateByEmail(String email) {
//		Optional<VerificationCode> result = verificationcodeRepo.findByEmail(email);
//		 return;
//		}

//	// 刪除單筆
//	@Override
//	public void deleteByEmail(String email) {
//		Boolean result = verificationcodeRepo.existsByEmail(email);
//
//		if (result) {
//			verificationcodeRepo.deleteByEmail(email);
//		}
//	}

	// 確認id存在
//	@Override
//	public Boolean findById(Integer id) {
//		Boolean result = memberRepo.existsById(id);
//		return result;
//	}

//	@Override
//	public VerificationCode findByEmail(String email) {
//		Optional<VerificationCode> resultBean = verificationcodeRepo.findByEmail(email);
//		if(resultBean.isPresent()) {
//			return resultBean.get();
//		}
//		return null;
//	}
}
