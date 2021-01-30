package com.ribeiro.mercadoEletronicoApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribeiro.mercadoEletronicoApi.model.entity.Items;
import com.ribeiro.mercadoEletronicoApi.model.repository.ItemsRepository;

@Service
public class ItemsService {

	@Autowired
	private ItemsRepository itemsRepository;
	
	public List<Items> findAll() {
		return itemsRepository.findAll();
	}
	
	public Items findById(Long id) {
		Optional<Items> items = itemsRepository.findById(id); 
		return items.get();
	}

}
