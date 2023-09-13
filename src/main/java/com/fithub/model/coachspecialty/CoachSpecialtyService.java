package com.fithub.model.coachspecialty;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fithub.model.department.Department;

@Service
public class CoachSpecialtyService implements ICoachSpecialtyService{

	@Autowired
	private CoachSpecialtyRespository cRepo;

	@Override
	public boolean insert(CoachSpecialty cBean) {
		CoachSpecialty result = cRepo.findCoachSpecialtyByEmpidSpecId(cBean.getEmployeeid(), cBean.getSpecialtyid());
		System.out.println("result-------" + result);
		if(result == null) {
			CoachSpecialty resultBean = cRepo.save(cBean);
			if (resultBean != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(CoachSpecialty cBean) {
		CoachSpecialty result = cRepo.findCoachSpecialtyByEmpidSpecId(cBean.getEmployeeid(), cBean.getSpecialtyid());
		if(result == null) {
			CoachSpecialty resultBean = cRepo.save(cBean);
			if (resultBean != null) {
				return true;
			}
		}
	
		return false;
	}

	@Override
	public void deleteById(Integer id) {
		cRepo.deleteById(id);
	}

	@Override
	public CoachSpecialty findById(Integer id) {
		Optional<CoachSpecialty> optional = cRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public List<CoachSpecialty> findAll() {
		List<CoachSpecialty> list = cRepo.findAll();
		return list;
	}

	@Override
	public long count(String name) {
		long count = cRepo.count(name);
		return count;
	}

	@Override
	public long count() {
		long count = cRepo.count();
		return count;
	}

	@Override
	public Page<CoachSpecialty> findPageByName(Integer pageNumber, Integer rows, String name) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");
		Page<CoachSpecialty> page = cRepo.searchByName(pgb, name);
		return page;
	}

	@Override
	public Page<CoachSpecialty> findByPage(Integer pageNumber, Integer rows) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");

		Page<CoachSpecialty> page = cRepo.findAll(pgb);

		return page;
	}
}
