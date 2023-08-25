package com.fithub.model.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
	public Boolean update(Cart cart) {
		Boolean result = exitsById(cart.getCartId());
		if (result) {
			cartRepo.saveAndFlush(cart);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = cartRepo.existsById(id);

		if (result) {
			cartRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		cartRepo.deleteAllById(selectIds);
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
