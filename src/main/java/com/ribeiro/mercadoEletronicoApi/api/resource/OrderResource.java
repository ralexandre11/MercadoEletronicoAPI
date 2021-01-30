package com.ribeiro.mercadoEletronicoApi.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;

@RestController
@RequestMapping(value = "/api/pedido")
public class OrderResource {

	@GetMapping
	public ResponseEntity<Order> findAll() {
		Order order = new Order(1L, "123456");
		return ResponseEntity.ok().body(order);
	}
}
