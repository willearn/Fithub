package com.fithub.model.verificationcode;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {
	
	//XiaoQing
	@Query("from VerificationCode where email = :email")
	VerificationCode findByEmail(@Param("email") String email);
	
//	boolean existsByEmail(String email);
	
	
//	// 由EMAIL刪除
//	@Modifying
//	@Transactional
//	@Query("delete from VerificationCode where email = :email")
//	void deleteByEmail(@Param("email") String email);

}
