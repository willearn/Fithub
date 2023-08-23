package com.fithub.model.activitypic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityPicService implements IActivityPicService {

	@Autowired
	private ActivityPicRepository apicRepo;

	@Override
	public List<ActivityPic> selectAllPic() {
		return apicRepo.findAll();
	}

	// 刪除多筆
	@Override
	public void deleteByActivityId(Iterable<Integer> selectIds) {
		for (Integer activityId : selectIds) {
			apicRepo.deleteByActivityId(activityId);
		}
	}

	@Override
	public Optional<ActivityPic> findById(Integer id) {
		return apicRepo.findById(id);
	}
}
