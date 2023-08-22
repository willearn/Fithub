package com.fithub.model.member;

import java.util.List;

public interface IMemberService {

	// 查詢全部
	List<Member> findAll();

	// 新增單筆活動
	Member insert(Member member);

	// 修改單筆
	void updateById(Member member);

	// 刪除單筆
	void deleteById(Integer id);

	// 確認id存在
//	Boolean findById(Integer id);
	
	public Member findById(Integer id);

}