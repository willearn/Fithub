package com.fithub.model.activitypic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityPicService implements IActivityPicService {

	@Autowired
	private ActivityPicRepository apicRepo;

	// 查詢全部
	@Override
	public List<ActivityPic> findAll() {
		List<ActivityPic> result = apicRepo.findAll();
		return result;
	}

	// 新增單筆
	@Override
	public ActivityPic insert(ActivityPic activityPic) {
		ActivityPic result = apicRepo.save(activityPic);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(ActivityPic activityPic) {
		Boolean result = apicRepo.existsById(activityPic.getApicid());
		if (result) {
			apicRepo.saveAndFlush(activityPic);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = apicRepo.existsById(id);
		if (result) {
			apicRepo.deleteById(id);
		}
	}

	// 刪除多筆
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		apicRepo.deleteAllById(selectIds);
	}
}
