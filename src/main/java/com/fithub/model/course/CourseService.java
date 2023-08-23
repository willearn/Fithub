package com.fithub.model.course;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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
	public void updateById(Course course) {
		Boolean result = exitsById(course.getCourseId());
		if (result) {
			courseRepo.saveAndFlush(course);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = courseRepo.existsById(id);

		if (result) {
			courseRepo.deleteById(id);
		}
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

}
