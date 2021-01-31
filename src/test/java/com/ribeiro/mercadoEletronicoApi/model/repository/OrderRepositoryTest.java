package com.ribeiro.mercadoEletronicoApi.model.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ribeiro.mercadoEletronicoApi.model.entity.Item;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OrderRepositoryTest {

	@Autowired
	OrderRepository repository;

	@Autowired
	TestEntityManager testEntityManager;
	
	public static Order createTestOrder() {
		Order order = new Order(null, "123456", new ArrayList<>());
		order.getItems().add(new Item(null, "ITEM A", new BigDecimal(200), 2));
		order.getItems().add(new Item(null, "ITEM B", new BigDecimal(150), 1));
		return order;
	}

	@Test
	public void mustSaveOrderDatabase() {
		Order order = createTestOrder();
		
		Order orderSaved = repository.save(order);
		
		Assertions.assertThat(orderSaved.getId()).isNotNull();
	}
	
	@Test
	public void mustFindOrderByNumberOrder() {
		Order order = createTestOrder();
		testEntityManager.persist(order);
		
		testEntityManager.clear();
		Optional<Order> actual = repository.findByOrderNumber("123456");

		Assertions.assertThat(actual).isPresent();
		Assertions.assertThat(actual.get()).isNotSameAs(order);
	}
	
}