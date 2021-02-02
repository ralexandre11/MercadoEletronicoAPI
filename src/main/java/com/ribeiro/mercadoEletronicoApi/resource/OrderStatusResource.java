package com.ribeiro.mercadoEletronicoApi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderStatusDTO;
import com.ribeiro.mercadoEletronicoApi.resource.dto.StatusResponseDTO;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/status")
@RequiredArgsConstructor
public class OrderStatusResource {

	@Autowired
	private final OrderService service;

	@PostMapping
	public ResponseEntity<StatusResponseDTO> verifyStatusOrder(@RequestBody final OrderStatusDTO statusDTO) {
		StatusResponseDTO statusResponse = new StatusResponseDTO();
		statusResponse.setPedido(statusDTO.getPedido());
		statusResponse.setStatus(service.checkStatus(statusDTO));
		return ResponseEntity.ok().body(statusResponse);
	}
	
}
