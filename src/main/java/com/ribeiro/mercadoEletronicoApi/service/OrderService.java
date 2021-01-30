package com.ribeiro.mercadoEletronicoApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id); 
		return order.get();
	}

}
