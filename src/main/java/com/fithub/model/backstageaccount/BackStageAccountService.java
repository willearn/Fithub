package com.fithub.model.backstageaccount;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BackStageAccountService implements IBackStageAccountService{

	@Autowired
	private IBackStageAccountDAO bDao;
	

	@Override
	public BackStageAccount insert(BackStageAccount bBean) {
		return bDao.insert(bBean);
	}

	@Override
	public BackStageAccount selectByAccount(String employeeaccount) {
		return bDao.selectByAccount(employeeaccount);
	}

	@Override
	public List<BackStageAccount> selectAll() {
		return bDao.selectAll();
	}

	@Override
	public BackStageAccount update(String employeeaccount, String employeepassword, int loa) {
		return bDao.update(employeeaccount, employeepassword, loa);
	}

	@Override
	public boolean deleteByAccount(String employeeaccount) {
		return bDao.deleteByAccount(employeeaccount);
	}
	
	@Override
	public boolean checkLogin(BackStageAccount backstageaccount) {
		return bDao.checkLogin(backstageaccount);
	}

}
