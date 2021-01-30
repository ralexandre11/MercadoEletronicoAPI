package com.ribeiro.mercadoEletronicoApi.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_item")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="description")
	private String description;

	@Column(name="price")
	private BigDecimal price;

	@Column(name="quantity")
	private Integer quantity;
	
//	@Column(name = "id_order")
//	private Integer idOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "order_item_order_fk"))
	private Order order;
	
	@JsonIgnore
	public Order getOrder() {
	    return order;
	}
	
}
