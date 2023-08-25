package com.fithub.model.member;

import java.util.List;
import java.util.Optional;

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

	// 新增單筆
	@Override
	public Member insert(Member member) {
		Member result = memberRepo.save(member);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(Member member) {
		Member result = findById(member.getMemberid());
		if (result != null ) {
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

	//查詢單筆
	@Override
	public Member findById(Integer id) {
		Optional<Member> resultBean = memberRepo.findById(id);
		if(resultBean.isPresent()) {
			return resultBean.get();
		}
		return null;
	}
}
