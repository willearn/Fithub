package com.fithub.model.backstageaccount;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fithub.model.employee.Employee;

@Service
@Transactional
public class BackStageAccountService implements IBackStageAccountService {

	@Autowired
	private BackStageAccountRepository bRepo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	public boolean insert(BackStageAccount bBean) {
		try {
			BackStageAccount result = bRepo.findBackStageAccountByAccount(bBean.getEmployeeaccount());
			bBean.setEmployeepassword(pwdEncoder.encode(bBean.getEmployeepassword()));
			if (result == null) {
				BackStageAccount resultBean = bRepo.save(bBean);
				if (resultBean != null) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean update(BackStageAccount bBean) {
		try {
			if(bBean.getEmployeepassword() != null) {
				bBean.setEmployeepassword(pwdEncoder.encode(bBean.getEmployeepassword()));
				BackStageAccount result = bRepo.save(bBean);
				if (result != null) {
					return true;
				}
			}else {
				Integer result = bRepo.updateLoaByAccount(bBean.getLoa(), bBean.getEmployeeaccount());
				System.out.println(result);
				if(result != 0) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void deleteBackStageAccountByAccount(String account) {
		try {
			bRepo.deleteBackStageAccountByAccount(account);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BackStageAccount findBackStageAccountByAccount(String account) {
		try {
			BackStageAccount resultBean = bRepo.findBackStageAccountByAccount(account);

			if (resultBean != null) {
				return resultBean;
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<BackStageAccount> findAll() {
		List<BackStageAccount> list = bRepo.findAll();
		return list;
	}

	// 找所有的count
	@Override
	public long count() {
		long count = bRepo.count();
		return count;
	}

	// 找所有的count
	@Override
	public long count(String name) {
		long count = bRepo.count(name);
		return count;
	}

	@Override
	public Page<BackStageAccount> findByPage(Integer pageNumber, Integer rows) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");

		Page<BackStageAccount> page = bRepo.findAll(pgb);

		return page;
	}

	@Override
	public Page<Object[]> findPageByName(Integer pageNumber, Integer rows, String name) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");
		Page<Object[]> page = bRepo.searchByName(pgb, name);
		System.out.println(page);
		return page;
	}

	@Override
	public BackStageAccount checkLogin(BackStageAccount bBean) {
		try {
			System.out.println("NAME:" + bBean.getEmployeeaccount());
			BackStageAccount resultBean = bRepo.findBackStageAccountByAccount(bBean.getEmployeeaccount());
			System.out.println("checkLogin");
			System.out.println(resultBean);
			if (resultBean != null) {
				System.out.println("!=null");
				if (pwdEncoder.matches(bBean.getEmployeepassword(), resultBean.getEmployeepassword())) {
					System.out.println("PASS");
					return resultBean;
				}
			}
		} catch (Exception e) {
			System.out.println("FALSE");
			return null;
		}

		return null;

	}

	@Override
	public Integer checkLoa(String account) {
		Integer loa = bRepo.findLoaByAccount(account);
		return loa;
	}

}
