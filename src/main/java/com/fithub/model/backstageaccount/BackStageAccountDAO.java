package com.fithub.model.backstageaccount;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;



public interface BackStageAccountDAO extends JpaRepository<BackStageAccount, Integer>{
	
	@Query("from BackStageAccount where employeeaccount = :account")
	BackStageAccount findBackStageAccountByAccount(@Param("account") String account);
	
	@Modifying
    @Transactional
	@Query("delete from BackStageAccount where employeeaccount = :account")
    void deleteBackStageAccountByAccount(@Param("account") String account);
}
