package com.ribeiro.mercadoEletronicoApi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;

@Configuration
@Profile("test")
public class initialOrders implements CommandLineRunner {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Order order1 = new Order(null, "111111");
		Order order2 = new Order(null, "222222");
		Order order3 = new Order(null, "333333");
		
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
		
	}
	
}
