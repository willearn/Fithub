package com.fithub.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberService implements IMemberService {

	@Autowired
	private MemberRepository memberRepo;

	// 查詢全部
	@Override
	public List<Member> findAll() {
		List<Member> result = memberRepo.findAll();
		return result;
	}

	// 新增單筆活動
	@Override
	public Member insert(Member member) {
		Member result = memberRepo.save(member);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(Member member) {
		Boolean result = findById(member.getMemberid());
		if (result) {
			memberRepo.saveAndFlush(member);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = memberRepo.existsById(id);

		if (result) {
			memberRepo.deleteById(id);
		}
	}

	// 確認id存在
	@Override
	public Boolean findById(Integer id) {
		Boolean result = memberRepo.existsById(id);
		return result;
	}
}
