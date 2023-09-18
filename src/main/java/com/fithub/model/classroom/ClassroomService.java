package com.fithub.model.classroom;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService implements IClassroomService {

	@Autowired
	private ClassroomRepository classroomRepo;

	@Override
	public List<Classroom> findAll() {
		List<Classroom> result = classroomRepo.findAll();
		return result;
	}

	@Override
	public List<Map<String, Object>> findAllClassroomNamesAndIds() {
		List<Map<String, Object>> result = classroomRepo.findAllClassroomNamesAndIds();
		return result;
	}
	
	@Override
	public List<ClassroomDTO> getClassroomInfo() {
		List<ClassroomDTO> result = classroomRepo.getClassroomInfo();
		return result;
	}
	
	@Override
	public List<Object[]> findAllClassroomsWithoutDescriptionsAndPics() {
		List<Object[]> result = classroomRepo.findAllClassroomsWithoutDescriptionsAndPics();
		return result;
	}

	@Override
	public Classroom insert(Classroom classroom) {
		Classroom result = classroomRepo.save(classroom);
		return result;
	}

	@Override
	public void updateById(Classroom classroom) {
		Boolean result = exitsById(classroom.getClassroomId());
		if (result) {
			classroomRepo.saveAndFlush(classroom);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = classroomRepo.existsById(id);

		if (result) {
			classroomRepo.deleteById(id);
		}
	}

	@Override
	public Classroom findById(Integer id) {
		Optional<Classroom> result = classroomRepo.findById(id);
		return result.get();
	}

	// 確認id存在
	@Override
	public Boolean exitsById(Integer id) {
		Boolean result = classroomRepo.existsById(id);
		return result;
	}

	// 刪除多筆
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		classroomRepo.deleteAllById(selectIds);
	}

	@Override
	public List<Map<String, Object>> findClassesByDateAndTime(int classroomId, String classDate, String classTime) {
		List<Map<String, Object>> classesList=classroomRepo.findClassesByDateAndTime(classroomId, classDate, classTime);
		return classesList;
	}

	@Override
	public List<Map<String, Object>> findRentOrdersByDateAndTimeAndStatus(int classroomId, String rentdate,
			String renttime, String rentstatus) {
		List<Map<String, Object>> rentOrderList= classroomRepo.findRentOrdersByDateAndTimeAndStatus(classroomId, rentdate, renttime, rentstatus);
		return rentOrderList;
	}

	
}
