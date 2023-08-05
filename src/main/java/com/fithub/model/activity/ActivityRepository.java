package com.fithub.model.activity;


import org.springframework.data.jpa.repository.JpaRepository;



public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
//	@Transactional
//	@Modifying  // HQL 本身只支援 DQL, 要寫 @Modifying 才有支援 DML
//	@Query("update Activity set name = :newName where id = :id")
//	Integer updateNameById(@Param("id") Integer id, @Param("newName") String newName);
}