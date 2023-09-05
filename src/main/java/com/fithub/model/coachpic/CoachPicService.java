package com.fithub.model.coachpic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachPicService implements ICoachPicService{

	@Autowired
	private CoachPicRespository cRepo;
	
	@Override
	public boolean insert(CoachPic cBean) {
		CoachPic result = cRepo.findCoachPicyByEmpidCpicFile(cBean.getEmployeeid(), cBean.getCpicfile());
		if(result == null) {
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
		if(result == null) {
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
	public List<CoachPic> findAll() {
		List<CoachPic> list = cRepo.findAll();
		return list;
	}
}
