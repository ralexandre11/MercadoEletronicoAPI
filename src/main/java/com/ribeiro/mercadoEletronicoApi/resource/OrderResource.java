package com.ribeiro.mercadoEletronicoApi.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribeiro.mercadoEletronicoApi.model.entity.Item;
import com.ribeiro.mercadoEletronicoApi.model.entity.Order;
import com.ribeiro.mercadoEletronicoApi.resource.dto.ItemDTO;
import com.ribeiro.mercadoEletronicoApi.resource.dto.OrderDTO;
import com.ribeiro.mercadoEletronicoApi.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/pedido")
@RequiredArgsConstructor
public class OrderResource {

	@Autowired
	private final OrderService service;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		final List<Order> orders = service.all();
		List<OrderDTO> ordersDTO = ConvertListEntityDTO(orders);
		return ResponseEntity.ok().body(ordersDTO);
	}

	@GetMapping(value = "/{numberOrder}")
	public ResponseEntity<OrderDTO> findByNumber(@PathVariable final String numberOrder) {
		OrderDTO orderDTO = new OrderDTO();
		Optional<Order> order = service.getByNumber(numberOrder);
		if (order.isPresent()) {
		    Order orderFound = order.get();
			orderDTO = ConvertEntityDTO(orderFound);
		}
		return ResponseEntity.ok().body(orderDTO);
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

//	@PostMapping
//	public ResponseEntity save(@RequestBody final PersonDTO dto) {
//		try {
//			final Order order = convert(dto);
//			final Person personSaved = service.savePerson(person);
//			return new ResponseEntity<Person>(personSaved, HttpStatus.CREATED);
//		} catch (final MyRuleException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}

//	@GetMapping
//	public ResponseEntity<List<Person>> search(@RequestParam(value = "name", required = false) final String name,
//			@RequestParam(value = "cpf", required = false) final String cpf) {
//
//		final Person personFilter = new Person(null, name, cpf);
//		// TODO rename to service.search
//		final List<Person> personList = service.searchPerson(personFilter);
//		return ResponseEntity.ok(personList);
//	}
//
//	@PostMapping
//	// TODO PersonDTO deve user usado em toda parte no Resource (o resource
//	// usualmente não deve
//	// conhecer o objeto de negócio, apenas o DTO)
//	public ResponseEntity save(@RequestBody final PersonDTO dto) {
//		try {
//			// TODO deve ser feito na camada de serviço (PersonService)
//			final Person person = convert(dto);
//			// TODO rename to save
//			final Person personSaved = service.savePerson(person);
//			return new ResponseEntity<Person>(personSaved, HttpStatus.CREATED);
//			// TODO renomear exceção para PersonException
//		} catch (final MyRuleException e) {
//			// TODO o ideal aqui é lançar uma exceçao e tratar via exception mapper.
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//
//	@PutMapping("{id}")
//	public ResponseEntity update(@PathVariable("id") final Integer id, @RequestBody final PersonDTO dto) {
//		// TODO criar método update no serviço
//		return service.getPersonByID(id).map(entity -> {
//			try {
//				final Person person = convert(dto);
//				person.setId(entity.getId());
//				// TODO rename to update
//				final Person personSaved = service.updatePerson(person);
//				System.out.println(personSaved);
//				return ResponseEntity.ok(person);
//			} catch (final Exception e) {
//				return ResponseEntity.badRequest().body(e.getMessage());
//			}
//		}).orElseGet(() ->
//		// TODO Usar exceção NotFoundException
//		new ResponseEntity("Registro inexistente!", HttpStatus.BAD_REQUEST));
//	}
//
//	@DeleteMapping("{id}")
//	public ResponseEntity delete(@PathVariable("id") final Integer id) {
//		return service.getPersonByID(id).map(entity -> {
//			// TODO rename to delete
//			// TODO delete pode ser apenas por ID, não precisa do objeto
//			service.deletePerson(entity);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}).orElseGet(() -> new ResponseEntity("Registro inexistente!", HttpStatus.BAD_REQUEST));
//	}
//
//	// TODO migrar pra serviço
//	private Person convert(final PersonDTO dto) {
//		return new Person(null, dto.getName(), dto.getCpf());
//	}
}
