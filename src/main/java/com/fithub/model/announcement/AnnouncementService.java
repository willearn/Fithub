package com.fithub.model.announcement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService implements IAnnouncementService {

	@Autowired
	private AnnouncementRepository annRepository;

	// 查詢全部
	@Override
	public List<Announcement> findAll() {
		List<Announcement> result = annRepository.findAll();
		return result;
	}

	// 新增單筆
	@Override
	public Announcement insert(Announcement announcement) {
		Announcement result = annRepository.save(announcement);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(Announcement announcement) {
		Boolean result = findById(announcement.getAnnid());
		if (result) {
			annRepository.saveAndFlush(announcement);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = annRepository.existsById(id);
		if (result) {
			annRepository.deleteById(id);
		}
	}

	// 確認id存在
	@Override
	public Boolean findById(Integer id) {
		Boolean result = annRepository.existsById(id);
		return result;
	}
}
