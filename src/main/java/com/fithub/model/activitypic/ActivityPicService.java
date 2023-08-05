package com.fithub.model.activitypic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityPicService {

	@Autowired
	private ActivityPicRepository apicRepo;

	
	public List<ActivityPic> selectAllPic() {
		return apicRepo.findAll();
	}

	// 刪除多筆
	public void deleteByActivityId(Iterable<Integer> selectIds) {
		for (Integer activityId : selectIds) {
			apicRepo.deleteByActivityId(activityId);
		}
	}

	public Optional<ActivityPic> findById(Integer id) {
		return apicRepo.findById(id);
	}
}
