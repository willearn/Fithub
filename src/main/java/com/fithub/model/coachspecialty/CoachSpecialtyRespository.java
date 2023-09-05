package com.fithub.model.coachspecialty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoachSpecialtyRespository extends JpaRepository<CoachSpecialty, Integer>{

	@Query("from CoachSpecialty where employeeid = :empid AND specialtyid = :specid")
	CoachSpecialty findCoachSpecialtyByEmpidSpecId(@Param("empid") Integer empid,@Param("specid") Integer specid);
}
