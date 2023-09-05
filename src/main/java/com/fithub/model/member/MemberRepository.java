package com.fithub.model.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	//XiaoQing
	@Query("from Member where memberemail = :email")
	Member findMemberByEmail(@Param("email") String email);
}
