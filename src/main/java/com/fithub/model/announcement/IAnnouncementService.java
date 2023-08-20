package com.fithub.model.announcement;

import java.util.List;

public interface IAnnouncementService {

	// 查詢全部
	List<Announcement> findAll();

	// 新增單筆
	Announcement insert(Announcement announcement);

	// 修改單筆
	void updateById(Announcement announcement);

	// 刪除單筆
	void deleteById(Integer id);

	// 確認id存在
	Boolean findById(Integer id);

}