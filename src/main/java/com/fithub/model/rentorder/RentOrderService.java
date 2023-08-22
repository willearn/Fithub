package com.fithub.model.rentorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentOrderService implements IRentOrderService {

	@Autowired
	private RentOrderRespository rentOrderRespo;

	// 查詢全部
	@Override
	public List<RentOrder> findAll() {
		List<RentOrder> result = rentOrderRespo.findAll();
		return result;
	}
	// 新增單筆
	@Override
	public RentOrder insert(RentOrder rentOrder) {
		RentOrder result = rentOrderRespo.save(rentOrder);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(RentOrder rentOrder) {
		Boolean result = rentOrderRespo.existsById(rentOrder.getRentorderid());
		if (result) {
			rentOrderRespo.saveAndFlush(rentOrder);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = rentOrderRespo.existsById(id);

		if (result) {
			rentOrderRespo.deleteById(id);
		}
	}
}
