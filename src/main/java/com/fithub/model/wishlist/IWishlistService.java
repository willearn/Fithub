package com.fithub.model.wishlist;

import java.util.List;

public interface IWishlistService {

	// 查詢全部
	public List<Wishlist> findAll();

	// 新增單筆活動
	public Wishlist insert(Wishlist wishlist);

	// 修改單筆
	public Boolean update(Wishlist wishlist);

	// 刪除單筆
	public Boolean deleteById(Integer id);
	
	// 刪除多筆
	public void deleteAllById(Iterable<Integer> selectIds);

	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public Wishlist findById(Integer id);
}
