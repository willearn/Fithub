package com.fithub.model.activity;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;



public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	// william 取得活動內容
    @Query("SELECT a.activitydescription activitydescription,a.activityname activityname,a.activitydate activitydate FROM Activity a WHERE a.activityid = :activityid")
    Map<String, Object> findDescriptionDateNameById(@Param("activityid") String activityid);


    // william 將活動顯示改為否
    @Modifying
    @Transactional
    @Query("UPDATE Activity a SET  a.activitydisplay = :activitydisplay WHERE a.activityid = :activityid")
    void updateDisplayById(@Param("activityid") Integer activityid,@Param("activitydisplay") String activitydisplay);
    
    // william 篩選顯示=是,並且已到上架日期,如超過下架日期就將顯示更改為否
    @Query("SELECT a.activityid  activityid, a.activityname  activityname, a.activityoff  activityoff, a.activitypic  activitypic " +
            "FROM Activity a " +
            "WHERE a.activitydisplay = '是' AND a.activityon <= :currentDate " +
            "ORDER BY a.activityon DESC")
     List<Map<String, Object>> filteredAndSortedActivities(@Param("currentDate") Date currentDate);
 }

