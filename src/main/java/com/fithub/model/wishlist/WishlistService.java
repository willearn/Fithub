package com.fithub.model.wishlist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class WishlistService implements IWishlistService {

	@Autowired
	private WishlistRepository wishlistRepo;
	
	@Override
	public List<Wishlist> findAll() {
		return wishlistRepo.findAll();

	}

	@Override
	public Wishlist insert(Wishlist wishlist) {
		return wishlistRepo.save(wishlist);

	}

	@Override
	public void updateById(Wishlist wishlist) {
		Boolean result = exitsById(wishlist.getListId());
		if (result) {
			wishlistRepo.saveAndFlush(wishlist);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = wishlistRepo.existsById(id);

		if (result) {
			wishlistRepo.deleteById(id);
		}
	}

	@Override
	public Boolean exitsById(Integer id) {
		return wishlistRepo.existsById(id);

	}

	@Override
	public Wishlist findById(Integer id) {
		Optional<Wishlist> result = wishlistRepo.findById(id);
		return result.get();
	}

}
