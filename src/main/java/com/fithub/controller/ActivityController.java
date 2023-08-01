package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fithub.model.activity.Activity;
import com.fithub.service.ActivityService;


@Controller
public class ActivityController {
	
	@Autowired
	private ActivityService aService;
	
	
	@GetMapping("/activity/page")
	public String showActivitys(@RequestParam(name="p",defaultValue = "1") Integer pageNumber,Model model) {
		Page<Activity> page = aService.findByPage(pageNumber);

		model.addAttribute("page", page);

		return "activity/showActivitys";
	}
}
