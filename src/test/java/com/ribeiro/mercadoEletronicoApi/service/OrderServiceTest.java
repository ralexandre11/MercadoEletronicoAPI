package com.ribeiro.mercadoEletronicoApi.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ribeiro.mercadoEletronicoApi.exception.PersonalException;
import com.ribeiro.mercadoEletronicoApi.model.entity.Item;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.enums.Status;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderStatusDTO;
import com.ribeiro.mercadoEletronicoApi.service.impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	OrderService service;

	@Mock
	OrderRepository repository;

	@BeforeEach
	public void setup() {
		service = new OrderServiceImpl(repository);
	}

	@Test
	public void givenUnexistingOrderNumber_whenSave_thenOrderIsSaved() {
		// given
		String orderNumber = "123456";
		Mockito.when(repository.findByOrderNumber(orderNumber)).thenReturn(Optional.empty());
		
		Order newOrder = new Order(null, orderNumber, null);

		Order savedOrder = Mockito.mock(Order.class);
		Mockito.when(repository.save(newOrder)).thenReturn(savedOrder);

		// when
		Order actual = service.save(newOrder);

		// then
		Mockito.verify(repository).save(newOrder);
		Assertions.assertThat(actual).isSameAs(savedOrder);
	}

	@Test
	public void givenExistingOrderNumber_whenSave_thenOrderNumberAlreadyExists() {
		// given
		String orderNumber = "123456";
		// Order existingOrder = Mockito.mock(Order.class);
		Order existingOrder = new Order(null, orderNumber, null);
		Mockito.when(repository.findByOrderNumber(orderNumber)).thenReturn(Optional.of(existingOrder));

		// Order newOrder = Mockito.mock(Order.class);
		// Mockito.when(newOrder.getOrderNumber()).thenReturn(orderNumber);
		Order newOrder = new Order(null, orderNumber, null);

		// when
		PersonalException thrown = assertThrows(PersonalException.class, () -> service.save(newOrder));

		// then
		Assertions.assertThat(thrown).isNotNull();
		Assertions.assertThat(thrown.getMessage()).isEqualTo("Já existe uma pedido com este número!");
	}

	@Test
	public void givenStatus_whenOrderNumberNotFound_thenReturnInvalidOrder() {
		// given
		OrderStatusDTO statusDTO = new OrderStatusDTO("APROVADO", 3, BigDecimal.TEN, "123456");
		Mockito.when(repository.findByOrderNumber(statusDTO.getPedido())).thenReturn(Optional.empty());
		
		// when
		List<Status> statusResponse = service.checkStatus(statusDTO);

		// then
		Assertions.assertThat(statusResponse.contains(Status.CODIGO_PEDIDO_INVALIDO)).isTrue();
	}

	@Test
	public void givenStatus_whenStatusWasReprovado_thenReturnReprovado() {
		// given
		OrderStatusDTO statusDTO = new OrderStatusDTO("REPROVADO", 3, BigDecimal.TEN, "123456");
		Order existingOrder = Mockito.mock(Order.class);
		Mockito.when(repository.findByOrderNumber(statusDTO.getPedido())).thenReturn(Optional.of(existingOrder));
		
		// when
		List<Status> statusResponse = service.checkStatus(statusDTO);

		// then
		Assertions.assertThat(statusResponse.contains(Status.REPROVADO)).isTrue();
	}

	@Test
	public void givenStatus_whenItensWasEqualAndTotalWasEqual_thenReturnAprovado() {
		// given
		String orderNumber = "123456";
		OrderStatusDTO statusDTO = new OrderStatusDTO("APROVADO", 3, BigDecimal.TEN, orderNumber);
		Order existingOrder = Mockito.mock(Order.class);
		Mockito.when(repository.findByOrderNumber(statusDTO.getPedido())).thenReturn(Optional.of(existingOrder));
		Mockito.when(existingOrder.getTotal()).thenReturn(BigDecimal.TEN);
		Mockito.when(existingOrder.getTotalItems()).thenReturn(3);
		
		// when
		List<Status> statusResponse = service.checkStatus(statusDTO);

		// then
		Assertions.assertThat(statusResponse.contains(Status.APROVADO)).isTrue();
	}

	
	
}
