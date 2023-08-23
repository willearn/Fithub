package com.fithub.model.specialty;

import java.util.List;

public interface ISpecialtyService {

	public boolean insert(Specialty sBean);
	public boolean update(Specialty sBean);
	public void deleteById(Integer id);
	public Specialty findById(Integer id);
	public List<Specialty> findAll();
}
