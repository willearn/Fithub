package com.fithub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.fithub.model.linepay.CheckoutPaymentRequestForm;
import com.fithub.model.rentorder.IRentOrderService;
import com.fithub.model.rentorder.RentOrder;
import com.fithub.model.rentorder.RentOrderRepository;

@CrossOrigin
@RestController
@RequestMapping("/rent")
public class RentOrderController {

	@Autowired
	private IRentOrderService iRentOrderService;

	// 列出所有租借訂單
	@GetMapping("/list")
	public ResponseEntity<?> findAllRentOrders() {
		try {
			List<RentOrder> rentOrders = iRentOrderService.findAll();
			return new ResponseEntity<>(rentOrders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/{classroomId}")
	public ResponseEntity<?> findAllDateTimeFromRentOrderAndclass(@PathVariable("classroomId") Integer classroomId) {
		try {
			List<Object[]> findAllDateTimeFromRentOrderAndclass = iRentOrderService
					.findAllDateTimeFromRentOrderAndclass(classroomId);

			return new ResponseEntity<>(findAllDateTimeFromRentOrderAndclass, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/findallpage")
	public ResponseEntity<?> findAllPage(@RequestBody Map<String, Object> page) {
		try {
				// 第幾頁
				int number =  (int) page.get("number");
				// 幾筆資料
				int row = (int) page.get("row");
				String date = (String) page.get("date");
				if(date == "") {
					date = null;
				}
				System.out.println(date);
				//	Map由多個entrySet()組成
//				for (Map.Entry<String, Object> entry : page.entrySet()) {
//					String key = entry.getKey();
//					Object value = entry.getValue();
//					System.out.println("Key: " + key + ", Value: " + value);
//				}
			return new ResponseEntity<>(iRentOrderService.findAllPage(date,number,row), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 查詢場地是否被預訂或使用
	@PostMapping("/checkClassroomAvailability")
	public ResponseEntity<?> checkClassroomAvailability(@RequestBody RentOrder rentOrder) {

		Integer classroomid = rentOrder.getClassroomid();
		String rentdate = rentOrder.getRentdate();
		String renttime = rentOrder.getRenttime();

		// true為已被使用 false為未使用
		Boolean classroomAvailability = iRentOrderService.checkClassroomAvailability(classroomid, rentdate, renttime);

		try {
			return new ResponseEntity<>(classroomAvailability, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 新增租借訂單
	@PostMapping("/insert")
	public ResponseEntity<?> insertRentOrder(@RequestBody RentOrder rentOrder) {
		try {
			RentOrder rentOrder2 = iRentOrderService.insert(rentOrder);
			return new ResponseEntity<>(rentOrder2.getRentorderid(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 更新租借訂單
//	@PutMapping("/update")
//	public ResponseEntity<?> updateRentOrder(@RequestBody RentOrder rentOrder) {
//		try {
//			iRentOrderService.updateById(rentOrder);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	// 刪除單筆訂單
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRentOrder(@PathVariable("id") int id) {

		try {
			iRentOrderService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除多筆訂單
	@DeleteMapping("/delete/multiple")
	public ResponseEntity<?> deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
		try {
			iRentOrderService.deleteAllById(ids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 列出該會員所有租借訂單
		@GetMapping("/list/bymemberid/{id}")
		public ResponseEntity<?> findByMemberId(@PathVariable Integer id) {
			try {
				List<RentOrder> rentOrders = iRentOrderService.findByMemberId(id);
				
				for(int i = 0 ; i < rentOrders.size() ; i++) {
					RentOrder rentOrder = rentOrders.get(i);
					rentOrder.setMember(null);
					rentOrders.set(i, rentOrder);
				}
				
				return new ResponseEntity<>(rentOrders, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@PostMapping("/list/findPageByDate")
		public ResponseEntity<?> findPageByDate(@RequestBody String json) {
			System.out.println("JSON");
			System.out.println(json.toString());
			try {
				JSONObject obj = new JSONObject(json);

				JSONObject responseJson = new JSONObject();
				JSONArray array = new JSONArray();

				String rentDate = obj.isNull("rentDate") ? null : obj.getString("rentDate");

				long count;
				
				
				
				//有的話 依照name去搜尋有幾筆資料，沒有則搜尋全部
				if (rentDate != null && !rentDate.isEmpty()) {
					Page<RentOrder> page;
					count = iRentOrderService.count(obj.getInt("memberId") , obj.getString("rentDate"));
					page = iRentOrderService.findPageByDate(obj.getInt("start"), obj.getInt("rows"),
							Integer.parseInt(obj.getString("memberId")) ,obj.getString("rentDate"));
					
					responseJson.put("count", count);
					
					System.out.println("!=null");
					System.out.println(count);

					for (RentOrder rentOrder : page) {
						JSONObject item = new JSONObject()
								.put("rentorderid", rentOrder.getRentorderid())
								.put("rentdate", rentOrder.getRentdate())
								.put("renttime", rentOrder.getRenttime())
								.put("classroomName", rentOrder.getClassroom().getClassroomName())
								.put("rentstatus", rentOrder.getRentstatus())
								.put("rentamount", rentOrder.getRentamount());
						array = array.put(item);
					}
					
				}else {
					Page<RentOrder> page;
					count = iRentOrderService.count(obj.getInt("memberId"));
					page = iRentOrderService.findByPage(obj.getInt("start"), obj.getInt("rows"), obj.getInt("memberId"));

					responseJson.put("count", count);
					
					for (RentOrder rentOrder : page) {
						JSONObject item = new JSONObject()
								.put("rentorderid", rentOrder.getRentorderid())
								.put("rentdate", rentOrder.getRentdate())
								.put("renttime", rentOrder.getRenttime())
								.put("classroomName", rentOrder.getClassroom().getClassroomName())
								.put("rentstatus", rentOrder.getRentstatus())
								.put("rentamount", rentOrder.getRentamount());
						array = array.put(item);
					}
				}
				
				responseJson.put("list", array);
				return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
}
