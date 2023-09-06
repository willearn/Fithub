package com.fithub.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fithub.model.coachpic.CoachPic;
import com.fithub.model.coachpic.ICoachPicService;

@RestController
@CrossOrigin()
public class CoachPicController {

	@Autowired
	private ICoachPicService cService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/coachpics/{cid}")
	public ResponseEntity<CoachPic> findById(@PathVariable("cid") int cid) throws JsonProcessingException {
		try {
			CoachPic spec = cService.findById(cid);
			System.out.println("-----------------------------------------------------");
			if (spec != null) {
				System.out.println("-----------------------------------------------------");
				System.out.println(spec.getCpicfile());
				return new ResponseEntity<CoachPic>(spec, HttpStatus.OK);
				
			}
			return new ResponseEntity<CoachPic>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CoachPic>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/coachpics/byemp/{eid}")
	public ResponseEntity<?> findByEmpId(@PathVariable("eid") int eid) throws JsonProcessingException {
		try {
			List<Map<String , String>> spec = cService.findByEmpId(eid);
			
			System.out.println(spec);
			if (spec != null) {
				return new ResponseEntity<>(spec, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	

	@GetMapping("/coachpics")
	public ResponseEntity<List<CoachPic>> findAll() throws JsonProcessingException {
		List<CoachPic> spec = cService.findAll();

		if (spec != null) {
			return new ResponseEntity<List<CoachPic>>(spec, HttpStatus.OK);
		}

		return new ResponseEntity<List<CoachPic>>(spec, HttpStatus.NOT_FOUND);
	}

//	@PostMapping("/coachpics")
//	public ResponseEntity<Object> insert(@RequestBody CoachPic cBean) {
//		boolean result = cService.insert(cBean);
//		if(result){
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}

	@PostMapping("/coachpics")
	public ResponseEntity<Object> insert(@RequestBody Map<String, String> request) {
		try {
			System.out.println("request");
			System.out.println(request);
			Integer employeeid = Integer.parseInt(request.get("employeeid"));
			String base64Image = request.get("cpicfile");
			
			byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);

			CoachPic cBean = new CoachPic();
			cBean.setEmployeeid(employeeid);
			cBean.setCpicfile(imageBytes);

			boolean result = cService.insert(cBean);
			if (result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/coachpics/{cid}")
	public ResponseEntity<Object> delete(@PathVariable("cid") int cid) {
		cService.deleteById(cid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/coachpics/{cid}")
	public ResponseEntity<Object> updateById(@PathVariable("cid") int cid, @RequestBody CoachPic cBean) {
		if (cService.findById(cid) != null) {
			boolean result = cService.update(cBean);
			if (result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
//	@PostMapping("/coachpics/findPageByName")
//	public ResponseEntity<?> findPageByName(@RequestBody String json) {
//		System.out.println("JSON");
//		System.out.println(json.toString());
//		try {
//			JSONObject obj = new JSONObject(json);
//
//			JSONObject responseJson = new JSONObject();
//			JSONArray array = new JSONArray();
//			JSONArray array2 = new JSONArray();
//			
//			String name = obj.isNull("name") ? null : obj.getString("name");
//
//			long count;
//			
//			//有的話 依照name去搜尋有幾筆資料，沒有則搜尋全部
//			if (name != null) {
//				Page<Object[]> page;
//				count = cService.count(obj.getString("name"));
//				page = cService.findPageByName(obj.getInt("start"), obj.getInt("rows"),
//						obj.getString("name"));
//				
//				responseJson.put("count", count);
//
//				for (Object[] cp : page) {
//					JSONObject item = new JSONObject().put("employeename", cp[0])
//							.put("employeeid",cp[1])
//							.put("cpicid",cp[2])
//							.put("cpicfile",cp[3]);
////					CoachPic cpic = new CoachPic();
////					cpic.setCpicfile( cp[3]);
//					array = array.put(item);
////					array2 = array2.put(false)
//				}
//			}else {
//				Page<CoachPic> page;
//				count = cService.count();
//				page = cService.findByPage(obj.getInt("start"), obj.getInt("rows"));
//				
//				responseJson.put("count", count);
//
//				for (CoachPic cp : page) {
//					JSONObject item = new JSONObject()
//							.put("employeename", cp.getEmployee().getEmployeename())
//							.put("employeeid", cp.getEmployeeid())
//							.put("cpicid", cp.getCpicid())
//							.put("cpicfile", cp.getCpicfile());
//					array = array.put(item);
//				}
//			}
//			
//		
//
//			responseJson.put("list", array);
//			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
}
