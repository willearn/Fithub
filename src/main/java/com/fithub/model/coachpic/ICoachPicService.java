package com.fithub.model.coachpic;

import java.util.List;

public interface ICoachPicService {

	// 查詢全部
	List<CoachPic> findAll();

	// 新增單筆
	CoachPic insert(CoachPic coachPic);

	// 修改單筆
	void updateById(CoachPic coachPic);

	// 刪除單筆
	void deleteById(Integer id);

	// 確認id存在
	Boolean findById(Integer id);

}