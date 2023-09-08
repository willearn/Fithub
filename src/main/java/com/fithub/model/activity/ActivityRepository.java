package com.fithub.model.activity;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	// william 取得活動內容
    @Query("SELECT a.activitydescription FROM Activity a WHERE a.activityid = :activityid")
    String findActivitydescriptionById(@Param("activityid") String activityid);

}