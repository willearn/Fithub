package com.fithub.model.course;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public List<Course> findAll() {
		return courseRepo.findAll();
	}

	@Override
	public Course insert(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public Boolean update(Course course) {
		Boolean result = exitsById(course.getCourseId());
		if (result) {
			courseRepo.saveAndFlush(course);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = courseRepo.existsById(id);

		if (result) {
			courseRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		courseRepo.deleteAllById(selectIds);
		
	}

	@Override
	public Boolean exitsById(Integer id) {
		return courseRepo.existsById(id);

	}

	@Override
	public Course findById(Integer id) {
		Optional<Course> result = courseRepo.findById(id);
		return result.get();
	}

	@Override
	public Page<Course> findByPage(Integer pageNumber, Integer dataSize) {
		// 按照courseId降冪排序
		PageRequest pgb =PageRequest.of(pageNumber-1, dataSize, Sort.Direction.DESC, "courseId");
		
		Page<Course> page = courseRepo.findAll(pgb);
		return page;
	}

}
