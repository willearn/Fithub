package com.fithub.model.activity;

import java.util.List;


public interface IActivityService {

	List<Activity> findAll();

	Activity insert(Activity activity);

	void updateById(Activity activity);

	void deleteById(Integer id);

	Boolean exitsById(Integer id);

	void deleteAllById(Iterable<Integer> selectIds);



}