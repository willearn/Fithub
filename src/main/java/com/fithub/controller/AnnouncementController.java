package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> findAllAnnouncements() {
		try {
			List<Announcement> announcements = iannouncementService.findAll();
			return new ResponseEntity<>(announcements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 新增公告
	@PostMapping("/insert")
	public ResponseEntity<?> insertAnnouncement(@RequestBody Announcement announcement) {
		try {
			iannouncementService.insert(announcement);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 修改公告
	@PutMapping("/update")
	public ResponseEntity<?> updateAnnouncement(@RequestBody Announcement announcement) {
		try {
			iannouncementService.updateById(announcement);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除公告
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAnnouncement(@PathVariable("id") int id) {
		try {
			iannouncementService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除多筆公告
	@DeleteMapping("/delete/multiple")
	public ResponseEntity<?> deleteMultipleAnnouncements(@RequestBody List<Integer> ids) {
		try {
			iannouncementService.deleteAllById(ids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
