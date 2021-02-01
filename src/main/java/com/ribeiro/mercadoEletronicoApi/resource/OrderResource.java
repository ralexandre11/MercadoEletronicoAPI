package com.ribeiro.mercadoEletronicoApi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.mercadoEletronicoApi.exception.PersonalException;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderDTO;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/pedido")
@RequiredArgsConstructor
public class OrderResource {

	@Autowired
	private final OrderService service;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		final List<Order> orders = service.all();
		List<OrderDTO> ordersDTO = service.ConvertListEntityDTO(orders);
		return ResponseEntity.ok().body(ordersDTO);
	}

	@GetMapping(value = "/{numberOrder}")
	public ResponseEntity<OrderDTO> findByNumber(@PathVariable final String numberOrder) {
		OrderDTO orderDTO = new OrderDTO();
		Optional<Order> order = service.getByNumber(numberOrder);
		if (order.isPresent()) {
			Order orderFound = order.get();
			orderDTO = service.ConvertEntityDTO(orderFound);
		}
		return ResponseEntity.ok().body(orderDTO);
	}

	@PostMapping
	public ResponseEntity save(@RequestBody final OrderDTO orderDTO) {
		try {
			final Order order = service.ConvertDTOEntity(orderDTO);
			final Order orderSaved = service.save(order);
			final OrderDTO orderDTOSaved = service.ConvertEntityDTO(orderSaved);
			return new ResponseEntity<OrderDTO>(orderDTOSaved, HttpStatus.CREATED);
		} catch (final PersonalException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{orderNumber}")
	public ResponseEntity update(@PathVariable("orderNumber") final String orderNumber, @RequestBody final OrderDTO orderDTO) {
		return service.getByNumber(orderNumber).map(entity -> {
			try {
				final Order order = service.ConvertDTOEntity(orderDTO);
				order.setId(entity.getId());
				final Order orderSaved = service.update(order);
				final OrderDTO orderDTOSaved = service.ConvertEntityDTO(orderSaved);
				return ResponseEntity.ok(orderDTOSaved);
			} catch (final Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() ->
			new ResponseEntity("Registro inexistente!", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("{orderNumber}")
	public ResponseEntity delete(@PathVariable("orderNumber") final String orderNumber) {
		return service.getByNumber(orderNumber).map(entity -> {
			service.delete(entity);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Registro inexistente!", HttpStatus.BAD_REQUEST));
	}

}
