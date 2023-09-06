package com.fithub.model.backstageaccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fithub.model.employee.Employee;

import jakarta.transaction.Transactional;

public interface BackStageAccountRepository extends JpaRepository<BackStageAccount, Integer> {

	// XiaoQing
	@Query("from BackStageAccount where employeeaccount = :account")
	BackStageAccount findBackStageAccountByAccount(@Param("account") String account);

	// XiaoQing
	@Modifying
	@Transactional
	@Query("delete from BackStageAccount where employeeaccount = :account")
	void deleteBackStageAccountByAccount(@Param("account") String account);

	// XiaoQing
	@Query("select loa from BackStageAccount where employeeaccount = :account")
	Integer findLoaByAccount(@Param("account") String account);

	// XiaoQing 查看employeeid的有幾筆資料
	@Query("select count(*) from Employee as e inner join BackStageAccount as ba on e.employeeid = ba.employeeid where :name is null or e.employeename like %:name%")
	long count(@Param("name") String name);

	// XiaoQing
	@Query("select e.employeeid, ba.employeeaccount ,e.employeename , ba.loa from Employee as e inner join BackStageAccount as ba on e.employeeid = ba.employeeid where e.employeename like %:name%")
	Page<Object[]> searchByName(Pageable pageable, @Param("name") String name);

	// XiaoQing
	@Modifying
	@Transactional
	@Query("UPDATE BackStageAccount b SET  b.loa = :loa WHERE b.employeeaccount = :account")
	Integer updateLoaByAccount(@Param("loa") Integer loa, @Param("account") String account);
}
