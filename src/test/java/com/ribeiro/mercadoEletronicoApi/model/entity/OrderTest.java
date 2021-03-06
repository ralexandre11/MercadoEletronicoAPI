package com.ribeiro.mercadoEletronicoApi.model.entity;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

	public static Order createTestOrder() {
		Item item1 = new Item(null, null, BigDecimal.TEN, 1);
		Item item2 = new Item(null, null, BigDecimal.ONE, 2);
		Item item3 = new Item(null, null, BigDecimal.ZERO, 3);
		Order order = new Order();
		order.getItems().add(item1);
		order.getItems().add(item2);
		order.getItems().add(item3);
		return order;
	}	
	
	@Test
	void givenOrderWithSeveralItems_whenGetTotal_thenTotalIsCorrect() {
		// given
		Order order = createTestOrder();
		
		// when
		BigDecimal total = order.getTotal();

		// then
		Assertions.assertThat(total.doubleValue()).isEqualTo(12.0);
	}

	@Test
	void givenOrderWithSeveralItems_whenGetTotalItems_thenTotalIsCorrect() {
		// given
		Order order = createTestOrder();

		// when
		Integer total = order.getTotalItems();

		// then
		Assertions.assertThat(total).isEqualTo(6);
	}
	
}