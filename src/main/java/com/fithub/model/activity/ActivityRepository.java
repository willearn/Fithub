package com.fithub.model.activity;


import org.springframework.data.jpa.repository.JpaRepository;


public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
}