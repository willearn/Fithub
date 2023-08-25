package com.fithub.model.activitypic;

import java.util.List;

public interface IActivityPicService {
	
	List<ActivityPic> findAll();
	
	ActivityPic insert(ActivityPic activityPic);

	void deleteById(Integer id);

	void updateById(ActivityPic activityPic);

	void deleteAllById(Iterable<Integer> selectIds);
}