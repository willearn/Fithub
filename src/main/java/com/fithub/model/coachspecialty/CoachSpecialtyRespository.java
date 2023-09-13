package com.fithub.model.coachspecialty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoachSpecialtyRespository extends JpaRepository<CoachSpecialty, Integer>{
	// XiaoQing
	@Query("from CoachSpecialty where employeeid = :empid AND specialtyid = :specid")
	CoachSpecialty findCoachSpecialtyByEmpidSpecId(@Param("empid") Integer empid,@Param("specid") Integer specid);
	
	// XiaoQing
	@Query("select count(*) from CoachSpecialty as c left join Employee as e on c.employeeid = e.employeeid where e.employeename like %:name%")
	long count(@Param("name") String name);

	//XiaoQing
	@Query("from CoachSpecialty as c left join Employee as e on c.employeeid = e.employeeid where e.employeename like %:name%")
	Page<CoachSpecialty> searchByName(Pageable pgb,@Param("name") String name);
}
