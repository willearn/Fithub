package com.fithub.model.coachpic;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CoachPicService implements ICoachPicService {

	@Autowired
	private CoachPicRespository cRepo;

	@Override
	public boolean insert(CoachPic cBean) {
		CoachPic result = cRepo.findCoachPicyByEmpidCpicFile(cBean.getEmployeeid(), cBean.getCpicfile());
		if (result == null) {
			CoachPic resultBean = cRepo.save(cBean);
			if (resultBean != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(CoachPic cBean) {
		CoachPic result = cRepo.findCoachPicyByEmpidCpicFile(cBean.getEmployeeid(), cBean.getCpicfile());
		if (result == null) {
			CoachPic resultBean = cRepo.save(cBean);
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
	public CoachPic findById(Integer id) {
		Optional<CoachPic> optional = cRepo.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public List<Map<String , Object>> findByEmpId(Integer id) {
		try {
			List<Map<String , Object>> resultBeans = cRepo.findByEmpId(id);
			if (resultBeans != null) {
				return resultBeans;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public List<CoachPic> findAll() {
		List<CoachPic> list = cRepo.findAll();
		return list;
	}

	@Override
	public long count() {
		long count = cRepo.count();
		return count;
	}

	@Override
	public long count(String name) {
		long count = cRepo.count(name);
		return count;
	}

	@Override
	public Page<CoachPic> findByPage(Integer pageNumber, Integer rows) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");

		Page<CoachPic> page = cRepo.findAll(pgb);
		return page;
	}

	@Override
	public Page<Object[]> findPageByName(Integer pageNumber, Integer rows, String name) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");
		Page<Object[]> page = cRepo.searchByName(pgb, name);
		System.out.println(page);
		return page;
	}

}
