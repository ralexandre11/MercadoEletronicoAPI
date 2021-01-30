package com.ribeiro.mercadoEletronicoApi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribeiro.mercadoEletronicoApi.model.entity.Item;
import com.ribeiro.mercadoEletronicoApi.model.repository.ItemsRepository;

@Service
public class ItemsServiceImpl {

	@Autowired
	private ItemsRepository itemsRepository;
	
	public List<Item> findAll() {
		return itemsRepository.findAll();
	}
	
	public Item findById(Long id) {
		Optional<Item> items = itemsRepository.findById(id); 
		return items.get();
	}

}
