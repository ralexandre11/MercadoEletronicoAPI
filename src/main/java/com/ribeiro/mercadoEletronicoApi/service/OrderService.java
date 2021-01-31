package com.ribeiro.mercadoEletronicoApi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;

public interface OrderService {
	
	Order saveOrder(Order order);
	
	Order updateOrder(Order order);
	
	void deleteOrder(Order order);
	
	List<Order> all();
	
	Optional<Order> getByNumber(String orderNumber);

	void checkOrderNumber(String numberOrder);
	
	BigDecimal totalOrder();
	
	Integer totalQuantityItems();


}
