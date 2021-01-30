package com.ribeiro.mercadoEletronicoApi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ribeiro.mercadoEletronicoApi.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
