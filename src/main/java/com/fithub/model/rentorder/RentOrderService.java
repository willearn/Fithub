package com.fithub.model.rentorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentOrderService implements IRentOrderService {

	@Autowired
	private RentOrderRepository rentOrderRepo;

	
	// 查詢全部
	@Override
	public List<RentOrder> findAll() {
		List<RentOrder> result = rentOrderRepo.findAll();
		return result;
	}

	// 新增單筆
	@Override
	public RentOrder insert(RentOrder rentOrder) {
		RentOrder result = rentOrderRepo.save(rentOrder);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(RentOrder rentOrder) {
		Boolean result = rentOrderRepo.existsById(rentOrder.getRentorderid());
		if (result) {
			rentOrderRepo.saveAndFlush(rentOrder);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = rentOrderRepo.existsById(id);

		if (result) {
			rentOrderRepo.deleteById(id);
		}
	}

	// 刪除多筆
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		rentOrderRepo.deleteAllById(selectIds);
	}
	
	// 查詢全部 rentdate 和 renttime
	@Override
	public List<Object[]> findAllrentdateAndrenttime() {
		List<Object[]> result = rentOrderRepo.findAllrentdateAndrenttime();
		return result;
	}
}
