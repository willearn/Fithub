package com.fithub.model.activitypic;

import java.util.List;
import java.util.Optional;

public interface IActivityPicService {

	List<ActivityPic> selectAllPic();

	// 刪除多筆
	void deleteByActivityId(Iterable<Integer> selectIds);

	Optional<ActivityPic> findById(Integer id);

}