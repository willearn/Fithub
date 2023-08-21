package com.fithub.model.backstageaccount;

import java.util.List;


public interface IBackStageAccountService {
	public boolean insert(BackStageAccount dBean);
	public boolean update(BackStageAccount dBean);
	public void deleteBackStageAccountByAccount(String account);
	public BackStageAccount findBackStageAccountByAccount(String account);
	public List<BackStageAccount> findAll();
}
