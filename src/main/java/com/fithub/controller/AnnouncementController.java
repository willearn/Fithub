package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.announcement.Announcement;
import com.fithub.model.announcement.IAnnouncementService;

@CrossOrigin
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

	@Autowired
	private IAnnouncementService iannouncementService;

	// 列出所有公告
	@GetMapping("/list")
	public List<Announcement> findAllClassrooms() {
		return iannouncementService.findAll();
	}

	// 新增公告
	@PostMapping("/insert")
	public void insertClassroom(@RequestBody Announcement announcement) {
		iannouncementService.insert(announcement);
	}

	// 修改公告
	@PutMapping("/update")
	public void updateClassroom(@RequestBody Announcement announcement) {
		iannouncementService.updateById(announcement);
	}

	// 刪除公告
	@DeleteMapping("/delete/{id}")
	public void deleteClassroom(@PathVariable("id") int id) {
		iannouncementService.deleteById(id);
	}

	// 刪除多筆公告
	@DeleteMapping("/delete/multiple")
	public void deleteMultipleAnnouncements(@RequestBody List<Integer> ids) {
		iannouncementService.deleteAllById(ids);
	}
}
