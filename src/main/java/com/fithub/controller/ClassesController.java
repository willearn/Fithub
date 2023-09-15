package com.fithub.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.classes.Classes;
import com.fithub.model.classes.ClassesDto;
import com.fithub.model.classes.IClassesService;
import com.fithub.model.course.Course;
import com.fithub.model.daterange.DateRange;
import com.fithub.model.daterange.DateRangeService;

@RestController
@RequestMapping("/classes")
@CrossOrigin(exposedHeaders = { "total-pages", "number-of-elements" })
public class ClassesController {

	@Autowired
	private IClassesService cService;

	@Autowired
	private DateRangeService dService;

	@GetMapping("/{cid}")
	public ResponseEntity<?> findClass(@PathVariable("cid") int cid) {
		try {
			Classes resultBean = cService.findById(cid);
			return new ResponseEntity<>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAllClasses() {
		try {
			List<Classes> resultList = cService.findAll();
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Author : Chrislafolia 全部課堂分頁功能
	@GetMapping("/findAll/page")
	public ResponseEntity<?> showAllClassesInPage(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
			@RequestParam(name = "size", defaultValue = "6") Integer dataSize) {
		try {
			// course data 放body
			Page<Classes> page = cService.findByPage(pageNumber, dataSize);
			List<Classes> courseResultList = page.getContent();

			// TotalPages, numberOfElements 放header
			MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
			mvm.add("total-pages", Integer.toString(page.getTotalPages()));
			mvm.add("number-of-elements", Integer.toString(page.getNumberOfElements()));

			return new ResponseEntity<>(courseResultList, mvm, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping
	public ResponseEntity<?> insertClass(@RequestBody Classes cBean) {
		try {
			Classes resultBean = cService.insert(cBean);
			return new ResponseEntity<>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateClass(@RequestBody Classes cBean) {
		try {
			Boolean resultBoolean = cService.update(cBean);
			return new ResponseEntity<>(resultBoolean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteClass(@PathVariable("cid") int cid) {
		try {
			Boolean resultBoolean = cService.deleteById(cid);
			return new ResponseEntity<>(resultBoolean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/deleteMultiple")
	public ResponseEntity<?> deleteMultipleClasses(@RequestBody List<Integer> cids) {
		try {
			cService.deleteAllById(cids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAllclassDateAndclassTime")
	public ResponseEntity<?> findAllclassDateAndclassTime(@RequestBody Integer classroomId) {
		try {
			List<Object[]> resultList = cService.findAllclassDateAndclassTimeByClassroomId(classroomId);
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAllClassesInMonthRange/{cid}")
	public ResponseEntity<?> findAllByCourseIdAndDateRange(@PathVariable(value = "cid") Integer cid,
			@RequestParam(value = "monthBefore") Integer monthBefore,
			@RequestParam(value = "monthAfter") Integer monthAfter) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 取前monthBefore個月的第一天，及後monthAfter個月最後一天
		DateRange dateRange = dService.getRangeDate(monthBefore, monthAfter);
		String startDate = dateFormat.format(dateRange.getStartDate());
		String endDate = dateFormat.format(dateRange.getEndDate());

		try {
			List<ClassesDto> resultList = cService.findAllByCourseIdAndDateRange(cid, startDate, endDate);
			System.out.println(resultList);
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAllInMonthRange")
	public ResponseEntity<?> findAllByDateRange(@RequestParam(value = "monthBefore") Integer monthBefore,
			@RequestParam(value = "monthAfter") Integer monthAfter) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 取前monthBefore個月的第一天，及後monthAfter個月最後一天
		DateRange dateRange = dService.getRangeDate(monthBefore, monthAfter);
		String startDate = dateFormat.format(dateRange.getStartDate());
		String endDate = dateFormat.format(dateRange.getEndDate());

		try {
			List<ClassesDto> resultList = cService.findAllByDateRange(startDate, endDate);
			System.out.println(resultList);
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAllInMonthRange/page")
	public ResponseEntity<?> findAllByDateRangeInPage(@RequestParam(value = "monthBefore") Integer monthBefore,
			@RequestParam(value = "monthAfter") Integer monthAfter,
			@RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
			@RequestParam(name = "size", defaultValue = "10") Integer dataSize) {
		System.out.println(dataSize);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 取前monthBefore個月的第一天，及後monthAfter個月最後一天
		DateRange dateRange = dService.getRangeDate(monthBefore, monthAfter);
		String startDate = dateFormat.format(dateRange.getStartDate());
		String endDate = dateFormat.format(dateRange.getEndDate());

		try {
			Page<ClassesDto> page = cService.findAllByDateRangeInPage(startDate, endDate, pageNumber, dataSize);
			List<ClassesDto> courseResultList = page.getContent();
			System.out.println(courseResultList + "111");
			// TotalPages, numberOfElements 放header
			MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
			mvm.add("total-pages", Integer.toString(page.getTotalPages()));
			mvm.add("number-of-elements", Integer.toString(page.getNumberOfElements()));
			return new ResponseEntity<>(courseResultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/findClassesByIds")
	public ResponseEntity<?> findAllClassesByClassesId(@RequestBody List<Integer> classesIds) {
		System.out.println(classesIds);
		try {
			List<ClassesDto> resultList = cService.findClassesByClassesId(classesIds);
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAllClassesInMemberWishlist")
	public ResponseEntity<?> findWishlistClassesByMemberId(@RequestParam(value = "memberId") int memberId) {
		try {
			List<Map<String, Object>> resultList = cService.findWishlistClassesByMemberId(memberId);
			if (resultList != null) {
				return new ResponseEntity<>(resultList, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
