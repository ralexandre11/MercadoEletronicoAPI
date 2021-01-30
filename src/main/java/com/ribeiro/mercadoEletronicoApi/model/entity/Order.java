package com.ribeiro.mercadoEletronicoApi.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_order")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name="order_number")
	private String orderNumber;
	
//	@OneToMany(cascade=CascadeType.ALL, mappedBy="order", fetch=FetchType.EAGER, orphanRemoval=true)
	@Transient
	private List<Item> items = new ArrayList<>();
	
    public List<Item> getItems() {
		return new ArrayList<>(items);
	}

    public void addItem(Item item) {
        this.items.add(item);
    }

}
