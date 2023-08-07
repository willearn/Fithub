package com.fithub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fithub.model.activity.Activity;
import com.fithub.model.activity.ActivityService;
import com.fithub.model.activitypic.ActivityPic;
import com.fithub.model.activitypic.ActivityPicService;
import com.fithub.model.employee.Employee;
import com.fithub.model.employee.EmployeeRepository;

@Controller
public class ActivityController {

	@Autowired
	private ActivityService aService;

	@Autowired
	private EmployeeRepository eService;

	@Autowired
	private ActivityPicService apicService;

	// 搜員工給新增活動選擇
	@GetMapping("/activity/insert")
	public String addActivity(Model model) {
		List<Employee> employees = eService.findAll();
		model.addAttribute("employees", employees);
		return "activity/insertActivity";
	}

	// 新增單筆活動(可上傳多張圖片)
	@PostMapping("/activity/insert")
	public String addActivity(@ModelAttribute Activity activity, @RequestParam("pic") MultipartFile[] files)
			throws IOException {
		Activity newActivity = new Activity();
		newActivity.setActivityname(activity.getActivityname());
		newActivity.setActivitydescription(activity.getActivitydescription());
		newActivity.setActivitydate(activity.getActivitydate());
		newActivity.setActivityurl(activity.getActivityurl());
		newActivity.setEmployeeid(activity.getEmployeeid());
		newActivity.setActivitydisplay(activity.getActivitydisplay());
		newActivity.setActivityon(activity.getActivityon());
		newActivity.setActivityoff(activity.getActivityoff());
		newActivity.setActivitysort(activity.getActivitysort());

		List<ActivityPic> activityPicList = new ArrayList<>();
		for (MultipartFile file : files) {
			ActivityPic activityPic = new ActivityPic();
			byte[] photoByte = file.getBytes();
			activityPic.setPhotofile(photoByte);
			activityPic.setActivity(newActivity);

			activityPicList.add(activityPic);
		}

		newActivity.setActivitypic(activityPicList);

		aService.insert(newActivity);

		return "redirect:/activity/page";
	}

//	// 新增單筆活動
//	@PostMapping("/activity/insert")
//	public String addActivity(Activity activity) {
//		aService.insert(activity);
//		return "redirect:/activity/page";
//	}
	
	// // 新增多筆
	// @PostMapping("/activity/insertMultipleActivity")
	// public String insertMultipleActivity(@RequestBody List<Activity>
	// customerList) {
	// aService.insertAll(customerList);
	// return "redirect:/activity/page";
	// }

	// // 刪除單筆
	// @DeleteMapping("/activity/delete")
	// public String deleteActivity(@RequestParam("id") Integer id) {
	// aService.deleteById(id);
	// return "redirect:/activity/page";
	// }

	// 刪除多筆
	@DeleteMapping("/activity/delete")
	public String deletesActivity(@RequestParam("selectId") String selectId) {
		// 字串切割為字串陣列 Java 16後可以直接使用.toList(),不用collect;
		System.out.println(selectId);
		List<Integer> selectedIds = Arrays.stream(selectId.split(",")).map(Integer::valueOf)
				.collect(Collectors.toList());
		
//		apicService.deleteByActivityId(selectedIds);
//		aService.deletesActivity(selectedIds);
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

	// 搜尋活動並分頁
	@GetMapping("/activity/page")
	public String showActivitys(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber
			,@RequestParam(name = "selectedValue",defaultValue="5") String selected, Model model) {
		Page<Activity> page = aService.findByPage(pageNumber,Integer.parseInt(selected));
		Long countData = aService.countData();
		model.addAttribute("page", page);
		model.addAttribute("countData", countData);
		return "activity/showActivitys";
	}

	// 搜尋全部活動圖片資料
	@GetMapping("/activity/activitypic")
	public String showActivityPic(Model model) {
		List<ActivityPic> activitypic = apicService.selectAllPic();
		model.addAttribute("activityPic", activitypic);
		return "/activity/showActivityPic";
	}

	// 取得資料庫圖片
	@GetMapping("/activity/downloadPic")
	public ResponseEntity<byte[]> downloadActivityPhoto(@RequestParam("id") Integer id) {
		Optional<ActivityPic> optional = apicService.findById(id);

		if (optional.isEmpty()) {
			return null;
		}
		ActivityPic activityPic = optional.get();
		byte[] activityImageFile = activityPic.getPhotofile();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		// 要傳送的資料,headers,要回傳的狀態碼
		return new ResponseEntity<byte[]>(activityImageFile, headers, HttpStatus.OK);
	}
}
