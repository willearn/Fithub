package com.fithub.model.backstageaccount;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BackStageAccountService implements IBackStageAccountService{

	@Autowired
	private BackStageAccountRepository bRepo;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Override
	public boolean insert(BackStageAccount bBean) {
		BackStageAccount result = bRepo.findBackStageAccountByAccount(bBean.getEmployeeaccount());
		bBean.setEmployeepassword(pwdEncoder.encode(bBean.getEmployeepassword()));
		if(result == null) {
			BackStageAccount resultBean = bRepo.save(bBean);
			if(resultBean != null) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean update(BackStageAccount bBean) {
		bBean.setEmployeepassword(pwdEncoder.encode(bBean.getEmployeepassword()));
		BackStageAccount result = bRepo.save(bBean);
		if(result != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void deleteBackStageAccountByAccount(String account) {
		bRepo.deleteBackStageAccountByAccount(account);
	}
	
	@Override
	public BackStageAccount findBackStageAccountByAccount(String account) {
		BackStageAccount resultBean = bRepo.findBackStageAccountByAccount(account);
		
		if(resultBean != null) {
			return resultBean;
		}
		
		return null;
	}
	
	@Override
	public List<BackStageAccount> findAll(){
		List<BackStageAccount> list = bRepo.findAll();
		return list;
	}
	
	@Override
	public BackStageAccount checkLogin(BackStageAccount bBean) {
		System.out.println("NAME:" + bBean.getEmployeeaccount());
		BackStageAccount resultBean = bRepo.findBackStageAccountByAccount(bBean.getEmployeeaccount());
		System.out.println("checkLogin");
		System.out.println(resultBean);
		if(resultBean != null) {
			System.out.println("!=null");
			if(pwdEncoder.matches(bBean.getEmployeepassword(), resultBean.getEmployeepassword())) {
				System.out.println("PASS");
				return resultBean;
			}
		}
		System.out.println("FALSE");
		return null;
		
	}
	
	@Override
	public Integer checkLoa(String account) {
		Integer loa = bRepo.findLoaByAccount(account);
		return loa;
	}

}
