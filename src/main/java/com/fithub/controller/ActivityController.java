package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.activity.Activity;
import com.fithub.model.employee.Employee;
import com.fithub.model.employee.EmployeeRepository;
import com.fithub.service.ActivityService;

@Controller
public class ActivityController {

	@Autowired
	private ActivityService aService;

	@Autowired
	private EmployeeRepository eService;

	// 搜員工給新增活動選擇
	@GetMapping("/activity/insert")
	public String addActivity(Model model) {
		List<Employee> employees = eService.findAll();
		model.addAttribute("employees", employees);
		return "activity/insertActivity";
	}

	// 新增單筆活動
	@PostMapping("/activity/insert")
	public String addActivity(Activity activity) {
		aService.insert(activity);
		return "redirect:/activity/page";
	}

	// 新增多筆
	@PostMapping("/activity/insertMultipleActivity")
	public String insertMultipleActivity(@RequestBody List<Activity> customerList) {
		aService.insertAll(customerList);
		return "redirect:/activity/page";
	}

	// 刪除單筆
	@DeleteMapping("/activity/delete")
	public String deleteActivity(@RequestParam("id") Integer id) {
		aService.deleteById(id);
		return "redirect:/activity/page";
	}

	// 傳值到修改頁面
	@PostMapping("/activity/update")
	public String updateActivity(@ModelAttribute Activity activity, @RequestParam("empName") String empname,
			Model model) {
		List<Employee> employees = eService.findAll();
		model.addAttribute("employees", employees);
		model.addAttribute("empname", empname);
		model.addAttribute("activity", activity);
		return "activity/updateActivity";
	}

	// 修改單筆
	@PutMapping("/activity/update")
	public String updateById(@ModelAttribute Activity activity) {
		aService.updateById(activity);
		return "redirect:/activity/page";
	}

	// 分頁
	@GetMapping("/activity/page")
	public String showActivitys(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model model) {
		Page<Activity> page = aService.findByPage(pageNumber);
		model.addAttribute("page", page);
		return "activity/showActivitys";
	}
}
