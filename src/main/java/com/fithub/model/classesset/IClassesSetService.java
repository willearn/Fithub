package com.fithub.model.classesset;

import java.util.List;

public interface IClassesSetService {

	// 查詢全部
	public List<ClassesSet> findAll();

	// 新增單筆活動
	public ClassesSet insert(ClassesSet classesSet);

	// 修改單筆
	public void updateById(ClassesSet classesSet);

	// 刪除單筆
	public void deleteById(Integer id);

	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public ClassesSet findById(Integer id);
}
