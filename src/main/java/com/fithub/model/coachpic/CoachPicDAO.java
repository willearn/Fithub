package com.fithub.model.coachpic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CoachPicDAO extends JpaRepository<CoachPic, Integer> {
	
	@Query("from CoachPic where employeeid = :empid AND cpicfile = :cpicfile")
	CoachPic findCoachPicyByEmpidCpicFile(@Param("empid") Integer empid,@Param("cpicfile") byte[] cpicfile);

}
