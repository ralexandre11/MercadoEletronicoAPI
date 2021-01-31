package com.ribeiro.mercadoEletronicoApi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ribeiro.mercadoEletronicoApi.model.entity.Item;

public interface ItemsRepository extends JpaRepository<Item, Long>{

}
