package com.ribeiro.mercadoEletronicoApi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ribeiro.mercadoEletronicoApi.exception.PersonalException;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;
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
	
}
