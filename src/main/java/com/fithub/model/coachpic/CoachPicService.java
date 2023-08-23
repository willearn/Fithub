package com.fithub.model.coachpic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachPicService implements ICoachPicService{

	@Autowired
	private CoachPicDAO cDao;
	
	@Override
	public boolean insert(CoachPic cBean) {
		CoachPic result = cDao.findCoachPicyByEmpidCpicFile(cBean.getEmployeeid(), cBean.getCpicfile());
		System.out.println("result-------" + result);
		if(result == null) {
			CoachPic resultBean = cDao.save(cBean);
			if (resultBean != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(CoachPic cBean) {
		CoachPic result = cDao.findCoachPicyByEmpidCpicFile(cBean.getEmployeeid(), cBean.getCpicfile());
		if(result == null) {
			CoachPic resultBean = cDao.save(cBean);
			if (resultBean != null) {
				return true;
			}
		}
	
		return false;
	}

	@Override
	public void deleteById(Integer id) {
		cDao.deleteById(id);
	}

	@Override
	public CoachPic findById(Integer id) {
		Optional<CoachPic> optional = cDao.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public List<CoachPic> findAll() {
		List<CoachPic> list = cDao.findAll();
		return list;
	}
}
