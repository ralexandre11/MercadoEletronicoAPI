package com.ribeiro.mercadoEletronicoApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository repository) {
		this.orderRepository = repository;
	}	

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		//Objects.requireNonNull(order)
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> all() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> getByNumber(String orderNumber) {
		return orderRepository.findByOrderNumber(orderNumber);
	}
	

}
