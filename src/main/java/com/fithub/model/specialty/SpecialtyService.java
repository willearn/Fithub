package com.fithub.model.specialty;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpecialtyService implements ISpecialtyService{

	@Autowired
	private SpecialtyDAO sDao;

	@Override
	public boolean insert(Specialty sBean) {
		Specialty result = sDao.findSpecialtyByName(sBean.getSpecialtyname());

		if (result == null) {
			Specialty resultBean = sDao.save(sBean);
			if (resultBean != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(Specialty sBean) {
		Specialty result = sDao.findSpecialtyByName(sBean.getSpecialtyname());
		if (result == null) {
			Specialty resultBean = sDao.save(sBean);
			if (resultBean != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteById(Integer id) {
		sDao.deleteById(id);
	}

	@Override
	public Specialty findById(Integer id) {
		Optional<Specialty> optional = sDao.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public List<Specialty> findAll() {
		List<Specialty> list = sDao.findAll();
		return list;
	}
	

}
