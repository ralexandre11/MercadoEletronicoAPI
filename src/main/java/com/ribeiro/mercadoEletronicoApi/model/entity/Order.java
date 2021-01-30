package com.ribeiro.mercadoEletronicoApi.model.entity;

import java.io.Serializable;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String orderNumber;
	
	public Order() {
	}

	public Order(Long id, String orderNumber) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}
