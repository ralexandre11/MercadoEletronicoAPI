package com.ribeiro.mercadoEletronicoApi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ribeiro.mercadoEletronicoApi.exception.PersonalException;
import com.ribeiro.mercadoEletronicoApi.model.entity.Item;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.model.enums.Status;
import com.ribeiro.mercadoEletronicoApi.model.repository.OrderRepository;
import com.ribeiro.mercadoEletronicoApi.resource.dto.ItemDTO;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderDTO;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderStatusDTO;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository repository) {
		this.orderRepository = repository;
	}

	@Override
	@Transactional
	public Order save(Order order) {
		checkOrderNumber(order.getOrderNumber());
		return orderRepository.save(order);
	}

	@Override
	public Order update(Order order) {
		Objects.requireNonNull(order.getId());
		return orderRepository.save(order);
	}

	@Override
	public void delete(Order order) {
		Objects.requireNonNull(order.getId());
		orderRepository.delete(order);
	}

	@Override
	public List<Order> all() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> getByNumber(String orderNumber) {
		return orderRepository.findByOrderNumber(orderNumber);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Order> getOrderByID(Long id) {
		return orderRepository.findById(id);
	}

	private void checkOrderNumber(String orderNumber) {
		Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
		if (order.isPresent()) {
			throw new PersonalException("Já existe uma pedido com este número!");
		}
	}

	@Override
	public void checkStatus(String orderNumber) {
		Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
		order.get().getTotal();
	}
	
	public List<OrderDTO> ConvertListEntityDTO(List<Order> orders) {
		List<OrderDTO> ordersDTO = new ArrayList<>();
		for (Order order : orders) {
			ordersDTO.add(ConvertEntityDTO(order));
		}
		return ordersDTO;
	}

	public OrderDTO ConvertEntityDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		List<ItemDTO> itemsDTO = new ArrayList<>();
		for (Item item : order.getItems()) {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setDescricao(item.getDescription());
			itemDTO.setPrecoUnitario(item.getPrice());
			itemDTO.setQtd(item.getQuantity());
			itemsDTO.add(itemDTO);
		}
		orderDTO.setPedido(order.getOrderNumber());
		orderDTO.setItens(itemsDTO);
		return orderDTO;
	}

	public Order ConvertDTOEntity(OrderDTO orderDTO) {
		Order order = new Order();
		List<Item> items = new ArrayList<>();
		for (ItemDTO itemDTO : orderDTO.getItens()) {
			Item item = new Item();
			item.setDescription(itemDTO.getDescricao());
			item.setPrice(itemDTO.getPrecoUnitario());
			item.setQuantity(itemDTO.getQtd());
			items.add(item);
		}
		order.setOrderNumber(orderDTO.getPedido());
		order.setItems(items);
		return order;
	}
	
	public List<Status> checkStatus(OrderStatusDTO statusDTO) {
		List<Status> status = new ArrayList<>();
		Optional<Order> orderOptional = getByNumber(statusDTO.getPedido());
		if (!orderOptional.isPresent()) {
			status.add(Status.CODIGO_PEDIDO_INVALIDO);
		} else {
			Order order = orderOptional.get();
			if (statusDTO.getStatus().equals("REPROVADO")) {
				status.add(Status.REPROVADO);
			} else if (statusDTO.getValorAprovado().compareTo(order.getTotal()) == 0  && statusDTO.getItensAprovados() == order.getTotalItems()) {
				status.add(Status.APROVADO);
			} else {
				if (statusDTO.getValorAprovado().compareTo(order.getTotal()) > 0) {
					status.add(Status.APROVADO_VALOR_A_MAIOR);
				} else if (statusDTO.getValorAprovado().compareTo(order.getTotal()) < 0){
					status.add(Status.APROVADO_VALOR_A_MENOR);
				}
				if (statusDTO.getItensAprovados() > order.getTotalItems()) {
					status.add(Status.APROVADO_QTD_A_MAIOR);
				} else if (statusDTO.getItensAprovados() < order.getTotalItems()) {
					status.add(Status.APROVADO_QTD_A_MENOR);
				}
			}
		}
		return status;
	}

	
}
