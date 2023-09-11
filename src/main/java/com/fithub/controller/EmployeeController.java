package com.fithub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fithub.model.coachpic.ICoachPicService;
import com.fithub.model.employee.Employee;
import com.fithub.model.employee.IEmployeeService;

@RestController
@CrossOrigin()
public class EmployeeController {

	@Autowired
	private IEmployeeService eService;

	@Autowired
	private JobTitleController jController;

	@Autowired
	private ICoachPicService cService;

	@GetMapping("/employees/{eid}")
	public ResponseEntity<Employee> findById(@PathVariable("eid") int eid) throws JsonProcessingException {
		Employee emp = eService.findById(eid);

		if (emp != null) {

			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}

		return new ResponseEntity<Employee>(emp, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employees")
	public ResponseEntity<?> findAll() throws JsonProcessingException, JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();

		List<Employee> emps = eService.findAll();

		if (emps != null) {
			for (Employee emp : emps) {
				JSONObject item = new JSONObject().put("employeeid", emp.getEmployeeid())
						.put("employeename", emp.getEmployeename()).put("employeeemail", emp.getEmployeeemail())
						.put("employeephone", emp.getEmployeephone()).put("employeegender", emp.getEmployeegender())
						.put("employeecity", emp.getEmployeecity()).put("employeezone", emp.getEmployeezone())
						.put("employeeaddress", emp.getEmployeeaddress()).put("deptid", emp.getDeptid())
						.put("deptname", emp.getDepartment().getDeptname()).put("jobtitleid", emp.getJobtitleid())
						.put("jobtitlename", emp.getJobtitle().getJobtitlename()).put("managerid", emp.getManagerid())
						.put("hiredate", emp.getHiredate()).put("resigndate", emp.getResigndate())
						.put("salary", emp.getSalary()).put("employeebirthday", emp.getEmployeebirthday())
						.put("employeeintroduction", emp.getEmployeeintroduction());
				array = array.put(item);

			}
			responseJson.put("list", array);
			System.out.println(responseJson);
			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
		}

		return new ResponseEntity<>(responseJson.toString(), HttpStatus.NOT_FOUND);
	}

	// 分頁查詢
	// json會傳送{"start":1,"rows":"5","name":"","sortOrder":"asc","sortType":"id"}
	// 會用到裡面的start 和 rows 從第幾頁開始及幾筆資料
	@PostMapping("/employees/findByPage")
	public ResponseEntity<?> findByPage(@RequestBody String json) {
		System.out.println(json.toString());
		try {
			JSONObject obj = new JSONObject(json);

			JSONObject responseJson = new JSONObject();
			JSONArray array = new JSONArray();

			long count = eService.count();
			responseJson.put("count", count);

			Page<Employee> page = eService.findByPage(obj.getInt("start"), obj.getInt("rows"));

			for (Employee emp : page) {
				JSONObject item = new JSONObject().put("employeeid", emp.getEmployeeid())
						.put("employeename", emp.getEmployeename()).put("employeeemail", emp.getEmployeeemail())
						.put("employeephone", emp.getEmployeephone()).put("employeegender", emp.getEmployeegender())
						.put("employeecity", emp.getEmployeecity()).put("employeezone", emp.getEmployeezone())
						.put("employeeaddress", emp.getEmployeeaddress()).put("deptid", emp.getDeptid())
						.put("deptname", emp.getDepartment().getDeptname()).put("jobtitleid", emp.getJobtitleid())
						.put("jobtitlename", emp.getJobtitle().getJobtitlename()).put("managerid", emp.getManagerid())
						.put("hiredate", emp.getHiredate()).put("resigndate", emp.getResigndate())
						.put("salary", emp.getSalary()).put("employeebirthday", emp.getEmployeebirthday())
						.put("employeeintroduction", emp.getEmployeeintroduction());
				array = array.put(item);
			}

			responseJson.put("list", array);
			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/employees/findPageByName")
	public ResponseEntity<?> findPageByName(@RequestBody String json) {
		System.out.println(json.toString());
		try {
			JSONObject obj = new JSONObject(json);

			JSONObject responseJson = new JSONObject();
			JSONArray array = new JSONArray();

			String name = obj.isNull("name") ? null : obj.getString("name");

			long count;
			Page<Employee> page;

			// 有的話 依照name去搜尋有幾筆資料，沒有則搜尋全部
			if (name != null) {
				count = eService.count(obj.getString("name"));
				page = eService.findPageByName(obj.getInt("start"), obj.getInt("rows"), obj.getString("name"));
			} else {
				count = eService.count();
				page = eService.findByPage(obj.getInt("start"), obj.getInt("rows"));
			}

			responseJson.put("count", count);

			for (Employee emp : page) {
				System.out.println("emp");
				System.out.println(emp.getEmployeename());
				JSONObject item = new JSONObject().put("employeeid", emp.getEmployeeid())
						.put("employeename", emp.getEmployeename()).put("employeeemail", emp.getEmployeeemail())
						.put("employeephone", emp.getEmployeephone()).put("employeegender", emp.getEmployeegender())
						.put("employeecity", emp.getEmployeecity()).put("employeezone", emp.getEmployeezone())
						.put("employeeaddress", emp.getEmployeeaddress()).put("deptid", emp.getDeptid())
						.put("deptname", emp.getDepartment().getDeptname()).put("jobtitleid", emp.getJobtitleid())
						.put("jobtitlename", emp.getJobtitle().getJobtitlename()).put("managerid", emp.getManagerid())
						.put("hiredate", emp.getHiredate()).put("resigndate", emp.getResigndate())
						.put("salary", emp.getSalary()).put("employeebirthday", emp.getEmployeebirthday())
						.put("employeeintroduction", emp.getEmployeeintroduction());
				array = array.put(item);
			}

			responseJson.put("list", array);
			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/employees/find")
	public ResponseEntity<?> find(@RequestBody String json) throws JsonProcessingException, JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();

		long count = eService.count(json);
		responseJson.put("count", count);

		List<Employee> emps = eService.findAll();

		if (emps != null) {
			for (Employee emp : emps) {
				JSONObject item = new JSONObject().put("employeeid", emp.getEmployeeid())
						.put("employeename", emp.getEmployeename()).put("employeeemail", emp.getEmployeeemail())
						.put("employeephone", emp.getEmployeephone()).put("employeegender", emp.getEmployeegender())
						.put("employeecity", emp.getEmployeecity()).put("employeezone", emp.getEmployeezone())
						.put("employeeaddress", emp.getEmployeeaddress()).put("deptid", emp.getDeptid())
						.put("deptname", emp.getDepartment().getDeptname()).put("jobtitleid", emp.getJobtitleid())
						.put("jobtitlename", emp.getJobtitle().getJobtitlename()).put("managerid", emp.getManagerid())
						.put("hiredate", emp.getHiredate()).put("resigndate", emp.getResigndate())
						.put("salary", emp.getSalary()).put("employeebirthday", emp.getEmployeebirthday())
						.put("employeeintroduction", emp.getEmployeeintroduction());
				array = array.put(item);
			}
			responseJson.put("list", array);
			System.out.println(responseJson);
			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
		}

		return new ResponseEntity<>(responseJson.toString(), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> insert(@RequestBody Employee eBean) {
		boolean result = eService.insert(eBean);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/employees/{eid}")
	public ResponseEntity<Object> updateById(@PathVariable("eid") int eid, @RequestBody Employee eBean) {
		System.out.println("eBean : " + eBean.getDeptid());
		if (eService.findById(eid) != null) {
			boolean result = eService.update(eBean);
			if (result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/employees/{eid}")
	public ResponseEntity<Object> delete(@PathVariable("eid") int eid) {
		eService.deleteById(eid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/employees/managers")
	public ResponseEntity<List<Employee>> findManagers() {
		Integer jobTitleId = jController.findJobTitleIdByName("主管");
		List<Employee> resultBeans = eService.findManagerByJobTitleId(jobTitleId);
		System.out.println("resultBeans--------------------" + resultBeans);
		if (resultBeans != null) {
			return new ResponseEntity<List<Employee>>(resultBeans, HttpStatus.OK);
		}
		return null;
	}

	@GetMapping("/employees/coachs")
	public ResponseEntity<List<Employee>> findCoachs() {
		Integer jobTitleId = jController.findJobTitleIdByName("教練");
		List<Employee> resultBeans = eService.findManagerByJobTitleId(jobTitleId);
		System.out.println("resultBeans--------------------" + resultBeans);
		if (resultBeans != null) {
			return new ResponseEntity<List<Employee>>(resultBeans, HttpStatus.OK);
		}
		return null;
	}

	@PostMapping("/employees/findCoachPageByName")
	public ResponseEntity<?> findCoachPageByName(@RequestBody String json) {
		Integer jobTitleId = jController.findJobTitleIdByName("教練");

		System.out.println(json.toString());
		try {
			JSONObject obj = new JSONObject(json);

			JSONObject responseJson = new JSONObject();
			JSONArray array = new JSONArray();

			String name = obj.isNull("name") ? null : obj.getString("name");

			long count;
			Page<Employee> page;

			// 有的話 依照name去搜尋有幾筆資料，沒有則搜尋全部
			if (name != null) {
				System.out.println("NAME != NULL");
				count = eService.countByJobTitleIdAndName(jobTitleId, name);
				page = eService.findCoachPageByName(obj.getInt("start"), obj.getInt("rows"), jobTitleId,
						obj.getString("name"));
			} else {
				count = eService.countByJobTitleId(jobTitleId);
				page = eService.findCoachByPage(obj.getInt("start"), obj.getInt("rows"), jobTitleId);
			}
			System.out.println("count----" + count);
			responseJson.put("count", count);

			for (Employee emp : page) {
				System.out.println("emp");
				System.out.println(emp.getJobtitleid());
				JSONObject item = new JSONObject().put("employeeid", emp.getEmployeeid()).put("employeename",
						emp.getEmployeename());
				array = array.put(item);
			}

			responseJson.put("list", array);
			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/employees/findCoachDataPicSpecialty")
	public ResponseEntity<?> findCoachDataPicSpecialty() {
		try {

			List<Map<String, Object>> result = eService.findCoachDataAndSpecialty();

			System.out.println(result.get(0).get("employeeid"));
			System.out.println(result.size());
			for (int i = 0; i < result.size(); i++) {
				List<Map<String, Object>> coachpic = cService.findByEmpId(Integer.parseInt(result.get(i).get("employeeid").toString()));
				
				// 創建一個包含 result 和 coachpic 的新 Map
	            Map<String, Object> combinedData = new HashMap<>();
	            combinedData.putAll(result.get(i)); // 將 result 中的數據複製到 combinedData 中
	            combinedData.put("coachpic", coachpic); // 添加 coachpic 數據到 combinedData 中
	            
	         // 將 combinedData 添加回 result 列表中
	            result.set(i, combinedData);

			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
