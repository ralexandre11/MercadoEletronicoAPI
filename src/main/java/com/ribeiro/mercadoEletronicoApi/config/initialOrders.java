package com.ribeiro.mercadoEletronicoApi.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ribeiro.mercadoEletronicoApi.model.entity.Item;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;

@Configuration
@Profile("production")
public class initialOrders implements CommandLineRunner {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Order order1 = new Order(null, "111111", new ArrayList<>());
			order1.getItems().add(new Item(null, "ITEM A", new BigDecimal(2), 2, null));
		
		Order order2 = new Order(null, "222222", new ArrayList<>());
			order2.getItems().add(new Item(null, "ITEM A", new BigDecimal(200), 2, null));
			order2.getItems().add(new Item(null, "ITEM B", new BigDecimal(150), 1, null));
			
		Order order3 = new Order(null, "333333", new ArrayList<>());
			order3.getItems().add(new Item(null, "ITEM A", new BigDecimal(300), 2, null));

		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
		System.out.println("Insert Data:");
		System.out.println("Orders: 111111, 222222, 333333");
	}
	
}
