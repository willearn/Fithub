package com.fithub.model.backstageaccount;

import java.util.List;

public interface IBackStageAccountService {
	public BackStageAccount insert(BackStageAccount bBean);

	public BackStageAccount selectByAccount(String employeeaccount);

	public List<BackStageAccount> selectAll();

	public BackStageAccount update(String employeeaccount, String employeepassword, int loa);

	public boolean deleteByAccount(String employeeaccount);
	
	public boolean checkLogin(BackStageAccount backstageaccount);
}
