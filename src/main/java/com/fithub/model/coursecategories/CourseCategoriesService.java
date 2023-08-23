package com.fithub.model.coursecategories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class CourseCategoriesService implements ICourseCategoriesService {

	@Autowired
	private CourseCategoriesRepository courseCategoriesRepo;
	
	@Override
	public List<CourseCategories> findAll() {
		return courseCategoriesRepo.findAll();
	}

	@Override
	public CourseCategories insert(CourseCategories courseCategories) {
		return courseCategoriesRepo.save(courseCategories);
	}

	@Override
	public void updateById(CourseCategories courseCategories) {
		Boolean result = exitsById(courseCategories.getCategoryId());
		if (result) {
			courseCategoriesRepo.saveAndFlush(courseCategories);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = courseCategoriesRepo.existsById(id);

		if (result) {
			courseCategoriesRepo.deleteById(id);
		}
	}

	@Override
	public Boolean exitsById(Integer id) {
		return courseCategoriesRepo.existsById(id);
	}

	@Override
	public CourseCategories findById(Integer id) {
		Optional<CourseCategories> result = courseCategoriesRepo.findById(id);
		return result.get();
	}

}
