package com.fithub.model.member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fithub.model.employee.Employee;

@Service
public class MemberService implements IMemberService {

	@Autowired
	private MemberRepository mRepo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	// 查詢全部
	@Override
	public List<Member> findAll() {
		List<Member> resultBeans = mRepo.findAll();
		return resultBeans;
	}

	// 新增單筆
	@Override
	public boolean insert(Member mBean) {
		try {
			Member result = mRepo.findMemberByEmail(mBean.getMemberemail());
			if (result == null) {

				// 設定日期
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String strDate = currentDate.format(dateFormatter);

				mBean.setMemberaccountsince(strDate);

				// 密碼加密
				mBean.setMemberpassword(pwdEncoder.encode(mBean.getMemberpassword()));

				Member resultBean = mRepo.save(mBean);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	// 修改單筆
	@Override
	public boolean update(Member mBean) {
		try {
			Optional<Member> optinoal = mRepo.findById(mBean.getMemberid());
			if (optinoal.isPresent()) {
				mRepo.save(mBean);
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = mRepo.existsById(id);

		if (result) {
			mRepo.deleteById(id);
		}
	}

	// 查詢單筆
	@Override
	public Member findById(Integer id) {
		Optional<Member> resultBean = mRepo.findById(id);
		if (resultBean.isPresent()) {
			return resultBean.get();
		}
		return null;
	}

	public Member findByEmail(String email) {
		try {
			Member resultBean = mRepo.findMemberByEmail(email);
			if (resultBean != null) {
				return resultBean;
			}

		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public Member checkLogin(Member mBean) {
		try {
			Member resultBean = mRepo.findMemberByEmail(mBean.getMemberemail());
			if (resultBean != null) {
				if (pwdEncoder.matches(mBean.getMemberpassword(), resultBean.getMemberpassword())) {
					return resultBean;
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;

	}
}
