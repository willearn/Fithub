package com.fithub.model.coachspecialty;

import java.util.List;


public interface ICoachSpecialtyService {
	public boolean insert(CoachSpecialty cBean);
	public boolean update(CoachSpecialty cBean);
	public void deleteById(Integer id);
	public CoachSpecialty findById(Integer id);
	public List<CoachSpecialty> findAll();
}
