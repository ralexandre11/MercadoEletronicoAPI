package com.ribeiro.mercadoEletronicoApi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.mercadoEletronicoApi.exception.PersonalException;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderDTO;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderStatusDTO;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/satus")
@RequiredArgsConstructor
public class OrderStatusResource {

	@Autowired
	private final OrderService service;

	@PostMapping
	public ResponseEntity<OrderStatusDTO> save(@RequestBody final OrderStatusDTO statusDTO) {
		try {
			final Order order = service.ConvertDTOEntity(orderDTO);
			final Order orderSaved = service.save(order);
			final OrderDTO orderDTOSaved = service.ConvertEntityDTO(orderSaved);
			return new ResponseEntity<OrderStatusDTO>(orderDTOSaved, HttpStatus.CREATED);
		} catch (final PersonalException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
