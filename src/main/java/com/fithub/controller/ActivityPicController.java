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

import com.fithub.model.activitypic.ActivityPic;
import com.fithub.model.activitypic.IActivityPicService;

@CrossOrigin
@RestController
@RequestMapping("/activitypic")
public class ActivityPicController {
	
	@Autowired
	private IActivityPicService iActivityPicService;
	
	// 列出所有圖片
		@GetMapping("/list")
		public ResponseEntity<?> findAllActivityPics() {
			try {
				List<ActivityPic> activities = iActivityPicService.findAll();
				return new ResponseEntity<>(activities, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// 新增單筆圖片
		@PostMapping("/insert")
		public ResponseEntity<?> insertActivityPic(@RequestBody ActivityPic activityPic) {
			try {
				iActivityPicService.insert(activityPic);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// 更新單筆圖片
		@PutMapping("/update")
		public ResponseEntity<?> updateActivityPic(@RequestBody ActivityPic activityPic) {
			try {
				iActivityPicService.updateById(activityPic);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// 刪除單筆圖片
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<?> deleteActivityPic(@PathVariable("id") int id) {
			try {
				iActivityPicService.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// 刪除多筆活動
		@DeleteMapping("/delete/multiple")
		public ResponseEntity<?> deleteMultipleActivityPics(@RequestBody List<Integer> ids) {
			try {
				iActivityPicService.deleteAllById(ids);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}
