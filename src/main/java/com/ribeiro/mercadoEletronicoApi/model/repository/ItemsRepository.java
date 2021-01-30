package com.ribeiro.mercadoEletronicoApi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ribeiro.mercadoEletronicoApi.model.entity.Items;

public interface ItemsRepository extends JpaRepository<Items, Long>{

}
