package com.fithub.model.coachpic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoachPicRespository extends JpaRepository<CoachPic, Integer> {

	// XiaoQing
	@Query("from CoachPic where employeeid = :empid AND cpicfile = :cpicfile")
	CoachPic findCoachPicyByEmpidCpicFile(@Param("empid") Integer empid, @Param("cpicfile") byte[] cpicfile);

	// XiaoQing 查看employeeid的有幾筆資料
	@Query("select count(*) from Employee as e inner join CoachPic as cp on e.employeeid = cp.employeeid where :name is null or e.employeename like %:name%")
	long count(@Param("name") String name);

	// XiaoQing
	@Query("select e.employeename , cp.employeeid , cp.cpicid,  cp.cpicfile from Employee as e inner join CoachPic as cp on e.employeeid = cp.employeeid where e.employeename like %:name%")
	Page<Object[]> searchByName(Pageable pageable, @Param("name") String name);

	// XiaoQing
	@Query("SELECT c.cpicid , c.employeeid, c.cpicfile FROM CoachPic c WHERE c.employeeid = :empid")
	List<CoachPic> findByEmpId(@Param("empid") Integer empid);
}
