package com.fithub.model.classesset;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class ClassesSetService implements IClassesSetService {

	@Autowired
	private ClassesSetRepository classesSetRepo;
	
	@Override
	public List<ClassesSet> findAll() {
		return classesSetRepo.findAll();

	}

	@Override
	public ClassesSet insert(ClassesSet classesSet) {
		return classesSetRepo.save(classesSet);

	}

	@Override
	public void updateById(ClassesSet classesSet) {
		Boolean result = exitsById(classesSet.getClassesSetId());
		if (result) {
			classesSetRepo.saveAndFlush(classesSet);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = classesSetRepo.existsById(id);

		if (result) {
			classesSetRepo.deleteById(id);
		}
	}

	@Override
	public Boolean exitsById(Integer id) {
		return classesSetRepo.existsById(id);

	}

	@Override
	public ClassesSet findById(Integer id) {
		Optional<ClassesSet> result = classesSetRepo.findById(id);
		return result.get();
	}

}
