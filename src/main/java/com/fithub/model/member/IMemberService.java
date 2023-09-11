package com.fithub.model.member;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fithub.model.backstageaccount.BackStageAccount;

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
	
	long count();
	public long count(String name);
	Page<Member> findByPage(Integer pageNumber, Integer rows);
	Page<Member> findPageByName(Integer pageNumber, Integer rows, String name);
	
	public boolean changePassword(Integer id, Map<String, String> checkPassword);
	public boolean forgotPassword(String email);
	public boolean resetPassword(Map<String, Object> checkPassword);

}