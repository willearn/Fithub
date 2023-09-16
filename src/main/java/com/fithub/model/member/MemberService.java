package com.fithub.model.member;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
				System.out.println(mBean.getMemberpassword());
				if (mBean.getMemberpassword() != null) {
					mBean.setMemberpassword(pwdEncoder.encode(mBean.getMemberpassword()));
				}
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

	@Override
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

	@Override
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

	@Override
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

	// 取得會員男女數量
	@Override
	public Map<String, Object> findmembergender() {
		int male = 0;
		int female = 0;

		List<Map<String, Object>> result = mRepo.findMemberGender();
		for (Map<String, Object> map : result) {
			if (map != null) {
				String gender = (String) map.get("membergender");
				if (gender.equals("男")) {
					male++;
				} else {
					female++;
				}
			}
		}

		Map<String, Object> gender = new HashMap<>();
		gender.put("male", male);
		gender.put("female", female);

		return gender;
	}

	// 取得會員年齡
	@Override
	public Map<String, Object> findMembermemberBirthday() {
		int age1To24 = 0;
		int age25To49 = 0;
		int age50To65 = 0;
		int age66To100 = 0;

		List<Map<String, Object>> result = mRepo.findMembermemberBirthday();
		for (Map<String, Object> map : result) {
			if (map != null) {
				String birthday = (String) map.get("memberbirthday");

				// 會員生日
				LocalDate birthDate = LocalDate.parse(birthday);
				// 當前日期
				LocalDate currentDate = LocalDate.now();
				// 計算年齡
				Period age = Period.between(birthDate, currentDate);

				// 打印年龄(只取年份)
				int years = age.getYears();

				// 根据年龄范围分配到相应变量
				if (years >= 1 && years <= 24) {
					age1To24++;
				} else if (years >= 25 && years <= 49) {
					age25To49++;
				} else if (years >= 50 && years <= 65) {
					age50To65++;
				} else if (years >= 66 && years <= 100) {
					age66To100++;
				}
			}

		}

		Map<String, Object> membersAge = new HashMap<>();
		membersAge.put("age1To24", age1To24);
		membersAge.put("age25To49", age25To49);
		membersAge.put("age50To65", age50To65);
		membersAge.put("age66To100", age66To100);

		return membersAge;
	}
	
	// 取得會員註冊時間
	@Override
	public int[] findMemberAccountsince() {
		int[] monthCounts = new int[12];
		List<Map<String, Object>> result = mRepo.findMemberAccountsince();
		for (Map<String, Object> map : result) {
			if (map != null) {
				String accountSince = (String) map.get("memberaccountsince");
				LocalDate accountDate = LocalDate.parse(accountSince);
				int year = accountDate.getYear();
				int month = accountDate.getMonthValue(); // 獲取月份（1-12）
				System.out.println(month);
				if (year == 2023) {
					// 使用 switch 增加對應月份
					switch (month) {
					case 1:
						monthCounts[0]++;
						break;
					case 2:
						monthCounts[1]++;
						break;
					case 3:
						monthCounts[2]++;
						break;
					case 4:
						monthCounts[3]++;
						break;
					case 5:
						monthCounts[4]++;
						break;
					case 6:
						monthCounts[5]++;
						break;
					case 7:
						monthCounts[6]++;
						break;
					case 8:
						monthCounts[7]++;
						break;
					case 9:
						monthCounts[8]++;
						break;
					case 10:
						monthCounts[9]++;
						break;
					case 11:
						monthCounts[10]++;
						break;
					case 12:
						monthCounts[11]++;
						break;
					default:
						break;
					}
				}
			}
		}
		return monthCounts;
	}
}
