package com.ribeiro.mercadoEletronicoApi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

@RestController
@RequestMapping(value = "/api/pedido")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> orders = orderService.all();
		return ResponseEntity.ok().body(orders);
	}
	
	@GetMapping(value = "/{numberOrder}")
	public ResponseEntity<Optional<Order>> findByNumber(@PathVariable String numberOrder) {
		Optional<Order> order = orderService.getByNumber(numberOrder);
		return ResponseEntity.ok().body(order);
	}
}
