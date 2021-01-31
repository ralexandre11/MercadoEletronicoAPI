package com.ribeiro.mercadoEletronicoApi.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public Item(Long id, String description, BigDecimal price, Integer quantity) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	@Id
	@Column(name = "id_item")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "id_order")
	private Integer idOrder;
	
	public BigDecimal getTotal() {
		return price.multiply(new BigDecimal(quantity));
	}

}