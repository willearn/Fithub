package com.fithub.model.activity;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	// william 取得活動內容
    @Query("SELECT a.activitydescription FROM Activity a WHERE a.activityid = :activityid")
    String findActivitydescriptionById(@Param("activityid") String activityid);

    
    // william 取得活動並依照是否顯示和排序
    @Query("SELECT a.activityid as activityid,a.activityname as activityname ,a.activityon as activityon,a.activityoff as activityoff,a.activitypic as activitypic FROM Activity a")
    List<Map<String,Object>> filteredAndSortedActivities();
    
    
    // william 取得活動並依照是否顯示和排序
//    @Query("SELECT a.activityid,a.activityname,a.activityon,a.activityoff,a.activitypic FROM Activity a")
//    List<Map<String,Object>> filteredAndSortedActivities();
}
