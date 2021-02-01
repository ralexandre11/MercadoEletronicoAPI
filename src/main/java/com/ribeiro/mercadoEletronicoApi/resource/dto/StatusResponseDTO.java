package com.ribeiro.mercadoEletronicoApi.resource.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponseDTO {

	private String pedido;
	private List<String> status;
	
}
