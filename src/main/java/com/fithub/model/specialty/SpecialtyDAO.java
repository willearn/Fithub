package com.fithub.model.specialty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialtyDAO extends JpaRepository<Specialty, Integer> {

	@Query("from Specialty where specialtyname = :name")
	Specialty findSpecialtyByName(@Param("name") String name);
}
