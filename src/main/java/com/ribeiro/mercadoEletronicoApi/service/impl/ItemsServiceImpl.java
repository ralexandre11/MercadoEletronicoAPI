package com.ribeiro.mercadoEletronicoApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribeiro.mercadoEletronicoApi.model.entity.Item;
import com.ribeiro.mercadoEletronicoApi.model.repository.ItemsRepository;
import com.ribeiro.mercadoEletronicoApi.service.ItemsService;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsRepository itemsRepository;
	
	public List<Item> all() {
		return itemsRepository.findAll();
	}
	
	public Item findById(Long id) {
		Optional<Item> items = itemsRepository.findById(id); 
		return items.get();
	}

}
