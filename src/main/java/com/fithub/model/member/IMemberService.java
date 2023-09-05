package com.fithub.model.member;

import java.util.List;

public interface IMemberService {

	// 查詢全部
	List<Member> findAll();

	// 新增單筆
	boolean insert(Member mBean);

	// 修改單筆
	public boolean update(Member mBean);

	// 刪除單筆
	void deleteById(Integer id);
	
	// 確認id存在
//	Boolean findById(Integer id);
	
	public Member findById(Integer id);

}