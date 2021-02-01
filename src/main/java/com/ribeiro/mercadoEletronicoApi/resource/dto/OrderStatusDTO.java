package com.ribeiro.mercadoEletronicoApi.resource.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {

	private String status;
	private Integer itensAprovados;
	private BigDecimal valorAprovado;
	private String pedido;

}
