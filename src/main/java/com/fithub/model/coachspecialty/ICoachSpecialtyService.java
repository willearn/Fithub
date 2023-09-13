package com.fithub.model.coachspecialty;

import java.util.List;

import org.springframework.data.domain.Page;



public interface ICoachSpecialtyService {
	public boolean insert(CoachSpecialty cBean);
	public boolean update(CoachSpecialty cBean);
	public void deleteById(Integer id);
	public CoachSpecialty findById(Integer id);
	public List<CoachSpecialty> findAll();
	public long count(String name);
	public long count();
	public Page<CoachSpecialty> findPageByName(Integer pageNumber, Integer rows, String name);
	public Page<CoachSpecialty> findByPage(Integer pageNumber, Integer rows);
}
