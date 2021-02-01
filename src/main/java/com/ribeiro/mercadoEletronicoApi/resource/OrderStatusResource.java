package com.ribeiro.mercadoEletronicoApi.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.enums.Status;
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
		statusResponse.setStatus(checkStatus(statusDTO));
		return ResponseEntity.ok().body(statusResponse);
	}

	public List<Status> checkStatus(OrderStatusDTO statusDTO) {
		List<Status> status = new ArrayList<>();
		Optional<Order> orderOptional = service.getByNumber(statusDTO.getPedido());
		if (!orderOptional.isPresent()) {
			status.add(Status.CODIGO_PEDIDO_INVALIDO);
		} else {
			Order order = orderOptional.get();
			System.out.println(statusDTO.getStatus());
			System.out.println(statusDTO.getValorAprovado());
			System.out.println(order.getTotal());
			System.out.println(statusDTO.getItensAprovados());
			System.out.println(order.getTotalItems());
			if (statusDTO.getStatus().equals("REPROVADO")) {
				status.add(Status.REPROVADO);
			} else if (statusDTO.getValorAprovado().compareTo(order.getTotal()) == 0  && statusDTO.getItensAprovados() == order.getTotalItems()) {
				status.add(Status.APROVADO);
			} else {
				if (statusDTO.getValorAprovado().compareTo(order.getTotal()) > 0) {
					status.add(Status.APROVADO_VALOR_A_MAIOR);
				} else if (statusDTO.getValorAprovado().compareTo(order.getTotal()) < 0){
					status.add(Status.APROVADO_VALOR_A_MENOR);
				}
				if (statusDTO.getItensAprovados() > order.getTotalItems()) {
					status.add(Status.APROVADO_QTD_A_MAIOR);
				} else if (statusDTO.getItensAprovados() < order.getTotalItems()) {
					status.add(Status.APROVADO_QTD_A_MENOR);
				}
			}
		}
		return status;
	}
	
	
}
