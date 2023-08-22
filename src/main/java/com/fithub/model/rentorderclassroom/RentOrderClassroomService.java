package com.fithub.model.rentorderclassroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentOrderClassroomService {
	
	@Autowired RentOrderClassroomRepository RentOrderClassroomRespo;
	
	
	// 新增單筆
	public RentOrderClassroom insert(RentOrderClassroom rentOrderClassroom) {
		RentOrderClassroom result = RentOrderClassroomRespo.save(rentOrderClassroom);
		return result;
	}
}
