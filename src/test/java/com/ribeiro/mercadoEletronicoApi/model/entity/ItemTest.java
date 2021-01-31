package com.ribeiro.mercadoEletronicoApi.model.entity;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	void givenItem_whenGetTotal_thenTotalIsCorrect() {
		// given
		Item item = new Item(null, null, BigDecimal.TEN, 3);

		// when
		BigDecimal total = item.getTotal();

		// then
		Assertions.assertThat(total.doubleValue()).isEqualTo(30.0);
	}

	@Test
	void givenItemWithZeroValue_whenGetTotal_thenTotalIsZero() {
		// given
		Item item = new Item(null, null, BigDecimal.ZERO, 10);

		// when
		BigDecimal total = item.getTotal();

		// then
		Assertions.assertThat(total).isZero();
	}
}