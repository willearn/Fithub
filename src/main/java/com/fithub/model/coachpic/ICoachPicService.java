package com.fithub.model.coachpic;

import java.util.List;


public interface ICoachPicService {
	public boolean insert(CoachPic cBean);
	public boolean update(CoachPic cBean);
	public void deleteById(Integer id);
	public CoachPic findById(Integer id);
	public List<CoachPic> findAll();
}
