package com.ribeiro.mercadoEletronicoApi.resource.dto;

import java.util.List;

import com.ribeiro.mercadoEletronicoApi.model.enums.Status;

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
	private List<Status> status;
	
}
