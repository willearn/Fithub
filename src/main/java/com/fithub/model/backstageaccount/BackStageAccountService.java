package com.fithub.model.backstageaccount;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BackStageAccountService implements IBackStageAccountService{

	@Autowired
	private BackStageAccountDAO bDao;
	
	@Override
	public boolean insert(BackStageAccount bBean) {
		BackStageAccount result = bDao.findBackStageAccountByAccount(bBean.getEmployeeaccount());
		
		if(result == null) {
			bDao.save(bBean);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean update(BackStageAccount bBean) {
		BackStageAccount result = bDao.save(bBean);
		if(result != null) {
			return true;
		}
		return false;
	}
	
	@Override
	public void deleteBackStageAccountByAccount(String account) {
		bDao.deleteBackStageAccountByAccount(account);
	}
	
	@Override
	public BackStageAccount findBackStageAccountByAccount(String account) {
		BackStageAccount resultBean = bDao.findBackStageAccountByAccount(account);
		
		if(resultBean != null) {
			return resultBean;
		}
		
		return null;
	}
	
	@Override
	public List<BackStageAccount> findAll(){
		List<BackStageAccount> list = bDao.findAll();
		return list;
	}

}
