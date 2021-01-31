package com.ribeiro.mercadoEletronicoApi.service.copy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ribeiro.mercadoEletronicoApi.exception.PersonalException;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;
import com.ribeiro.mercadoEletronicoApi.service.impl.OrderServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OrderServiceTest {

	OrderService service;
	OrderRepository repository;
	
	@BeforeEach
	public void setup() {
		repository = Mockito.mock(OrderRepository.class);
		service = new OrderServiceImpl(repository);
	}

	@Test()
	public void CheckOrderNumberExistAndReturnException() {
		Mockito.when(repository.findByOrderNumber(Mockito.anyString())).thenReturn(null);
		
		PersonalException thrown = assertThrows(PersonalException.class, () -> service.checkOrderNumber("123456");
		
		assertTrue(thrown.getMessage().contains("Já existe uma pedido com este número!"));
		
//		assertEquals("Já existe uma pedido com este número!", exception.getMessage());
		
//		assertTrue(thrown.getMessage().contains("Stuff"));
	}	
	
}
