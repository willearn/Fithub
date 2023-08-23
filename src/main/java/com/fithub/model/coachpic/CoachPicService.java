package com.fithub.model.coachpic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachPicService implements ICoachPicService {

	@Autowired
	private CoachPicRespository cPicRespository;

	// 查詢全部
	@Override
	public List<CoachPic> findAll() {
		List<CoachPic> result = cPicRespository.findAll();
		return result;
	}

	// 新增單筆
	@Override
	public CoachPic insert(CoachPic coachPic) {
		CoachPic result = cPicRespository.save(coachPic);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(CoachPic coachPic) {
		Boolean result = cPicRespository.existsById(coachPic.getCpicid());
		if (result) {
			cPicRespository.saveAndFlush(coachPic);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = cPicRespository.existsById(id);
		if (result) {
			cPicRespository.deleteById(id);
		}
	}

}
