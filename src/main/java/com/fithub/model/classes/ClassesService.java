package com.fithub.model.classes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesService implements IClassesService {

	@Autowired
	private ClassesRepository classesRepo;
	
	@Override
	public List<Classes> findAll() {
		return classesRepo.findAll();

	}

	@Override
	public Classes insert(Classes classes) {
		return classesRepo.save(classes);

	}

	@Override
	public Boolean update(Classes classes) {
		Boolean result = exitsById(classes.getClassId());
		if (result) {
			classesRepo.saveAndFlush(classes);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = classesRepo.existsById(id);

		if (result) {
			classesRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		classesRepo.deleteAllById(selectIds);
		
	}

	@Override
	public Boolean exitsById(Integer id) {
		return classesRepo.existsById(id);

	}

	@Override
	public Classes findById(Integer id) {
		Optional<Classes> result = classesRepo.findById(id);
		return result.get();
	}
	
	@Override
	public List<Object[]> findAllclassDateAndclassTime() {
		return classesRepo.findAllclassDateAndclassTime();

	}

}
