package com.fithub.model.coachpic;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.fithub.model.backstageaccount.BackStageAccount;


public interface ICoachPicService {
	public boolean insert(CoachPic cBean);
	public boolean update(CoachPic cBean);
	public void deleteById(Integer id);
	public CoachPic findById(Integer id);
	public List<Map<String , String>> findByEmpId(Integer id);
	public List<CoachPic> findAll();
	long count();
	public long count(String name);
	Page<CoachPic> findByPage(Integer pageNumber, Integer rows);
	Page<Object[]> findPageByName(Integer pageNumber, Integer rows, String name);
}
