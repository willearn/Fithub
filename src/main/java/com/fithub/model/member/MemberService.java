package com.fithub.model.member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fithub.util.EmailService;

@Service
public class MemberService implements IMemberService {

	@Autowired
	private MemberRepository mRepo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private EmailService eService;

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
				mBean.setMemberpassword(pwdEncoder.encode(mBean.getMemberpassword()));
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

	public Member checkGoogleLogin(Member mBean) {
		try {
			Member resultBean = mRepo.findMemberByEmail(mBean.getMemberemail());
			if (resultBean != null) {
				return resultBean;
			} else {
				// 設定日期
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String strDate = currentDate.format(dateFormatter);

				mBean.setMemberaccountsince(strDate);

				Member saveBean = mRepo.save(mBean);
				return saveBean;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean changePassword(Integer id, Map<String, String> checkPassword) {
		try {
			Optional<Member> resultBean = mRepo.findById(id);

			if (resultBean.isPresent()) {
				Member member = resultBean.get();
				String password = member.getMemberpassword();
				String oldpassword = checkPassword.get("oldpassword");
				String newpassword = checkPassword.get("newpassword");

				if (password == null) {
					System.out.println("password is null");
				} else {
					System.out.println("password not null");
				}

				if (oldpassword.isEmpty()) {
					System.out.println("old is empty");
				} else {
					System.out.println("old not empty");
				}

				if (password == null && oldpassword.isEmpty()) {
					member.setMemberpassword(pwdEncoder.encode(newpassword));
					Member saveBean = mRepo.save(member);

					if (saveBean != null) {
						return true;
					}
				}

				if (password != null && !oldpassword.isEmpty()) {
					if (pwdEncoder.matches(oldpassword, member.getMemberpassword())) {
						member.setMemberpassword(pwdEncoder.encode(newpassword));
						Member saveBean = mRepo.save(member);

						if (saveBean != null) {
							return true;
						}
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public long count() {
		long count = mRepo.count();
		return count;
	}

	@Override
	public long count(String name) {
		long count = mRepo.count(name);
		return count;
	}

	@Override
	public Page<Member> findByPage(Integer pageNumber, Integer rows) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "memberid");
		Page<Member> page = mRepo.findAll(pgb);
		return page;
	}

	@Override
	public Page<Member> findPageByName(Integer pageNumber, Integer rows, String name) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "memberid");
		Page<Member> page = mRepo.searchByName(pgb, name);
		System.out.println(page);
		return page;
	}

	@Override
	public boolean forgotPassword(String email) {
		try {
			Member resultBean = mRepo.findMemberByEmail(email);

			if (resultBean != null) {
				eService.sendForgotPasswordEmail(email);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean resetPassword(Map<String, Object> checkPassword) {
		try {
			String email = eService.verifyToken(checkPassword.get("token").toString());

			Member resultBean = mRepo.findMemberByEmail(email);

			if (resultBean != null) {
				resultBean.setMemberpassword(pwdEncoder.encode(checkPassword.get("newpassword").toString()));
				mRepo.save(resultBean);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
