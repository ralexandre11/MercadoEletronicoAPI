package com.ribeiro.mercadoEletronicoApi.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ribeiro.mercadoEletronicoApi.exception.PersonalException;
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
	@Transactional
	public Order saveOrder(Order order) {
		checkOrderNumber(order.getOrderNumber());
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

	@Override
	public void checkOrderNumber(String orderNumber) {
		Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
		if (order.isPresent()) {
			throw new PersonalException("Já existe uma pedido com este número!");
		}
	}
	
	@Override
	public BigDecimal totalOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer totalQuantityItems() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
