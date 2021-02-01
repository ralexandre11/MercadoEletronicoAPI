package com.ribeiro.mercadoEletronicoApi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ribeiro.mercadoEletronicoApi.model.entity.Item;

public interface ItemsRepository extends JpaRepository<Item, Long>{

}
