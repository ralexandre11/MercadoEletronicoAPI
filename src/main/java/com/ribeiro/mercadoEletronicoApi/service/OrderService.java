package com.ribeiro.mercadoEletronicoApi.service;

import java.util.List;
import java.util.Optional;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderDTO;

public interface OrderService {
	
	Order save(Order order);
	
	Order update(Order order);
	
	void delete(Order order);
	
	List<Order> all();
	
	Optional<Order> getByNumber(String orderNumber);
	
	Optional<Order> getOrderByID(Long id);

	void checkStatus(String orderNumber /*, Status*/);

	List<OrderDTO> ConvertListEntityDTO(List<Order> orders);
	
	OrderDTO ConvertEntityDTO(Order order);

	Order ConvertDTOEntity(OrderDTO orderDTO);
	
}
