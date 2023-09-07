package com.fithub.model.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	//XiaoQing
	@Query("from Member where memberemail = :email")
	Member findMemberByEmail(@Param("email") String email);
	
	// XiaoQing 
	@Query("select count(*) from Member where :name is null or membername like %:name%")
	long count(@Param("name") String name);

	// XiaoQing
	@Query("from Member where membername like %:name%")
	Page<Member> searchByName(Pageable pageable, @Param("name") String name);
}
