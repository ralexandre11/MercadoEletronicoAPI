package com.ribeiro.mercadoEletronicoApi.resource.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private String pedido;
	private List<ItemDTO> itens;

}
