package com.fithub.model.activitypic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;



public interface ActivityPicRepository extends JpaRepository<ActivityPic, Integer> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM ActivityPic ap WHERE ap.activity.id = :activityId")
	void deleteByActivityId(@Param("activityId") Integer activityId);
	
}
