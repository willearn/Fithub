package com.fithub.model.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class CartService implements ICartService {

	@Autowired
	private CartRepository cartRepo;
	
	@Override
	public List<Cart> findAll() {
		return cartRepo.findAll();
	}

	@Override
	public Cart insert(Cart cart) {
		return cartRepo.save(cart);

	}

	@Override
	public void updateById(Cart cart) {
		Boolean result = exitsById(cart.getCartId());
		if (result) {
			cartRepo.saveAndFlush(cart);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = cartRepo.existsById(id);

		if (result) {
			cartRepo.deleteById(id);
		}
	}

	@Override
	public Boolean exitsById(Integer id) {
		return cartRepo.existsById(id);

	}

	@Override
	public Cart findById(Integer id) {
		Optional<Cart> result = cartRepo.findById(id);
		return result.get();
	}

}
