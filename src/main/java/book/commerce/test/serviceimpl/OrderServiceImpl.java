package book.commerce.test.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import book.commerce.test.entity.Address;
import book.commerce.test.entity.Book;
import book.commerce.test.entity.Order;
import book.commerce.test.entity.OrderDetails;
import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.repository.OrderRepository;
import book.commerce.test.service.AddressService;
import book.commerce.test.service.BookService;
import book.commerce.test.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private BookService bookService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderRepository orderRepository;

	private static int quantityCount = 0;

	@Override
	public Order createOrder(String addressId, List<List<String>> orders)
			throws EntityExistsException, EntityNotFoundException {
		Address address = this.addressService.readAddressById(addressId);
		Order order = new Order(false, address);
		List<Book> books = new ArrayList<>();
		List<Long> quantities = new ArrayList<>();
		this.orderRepository.save(order);
		for (String bookId : orders.get(quantityCount)) {
			books.add(this.bookService.readBookById(bookId));
		}
		orders.get(1).forEach(quantity -> quantities.add(Long.parseLong(quantity)));
		quantityCount = 0;
		books.forEach(book -> {
			long currentStock = book.getStock() - quantities.get(quantityCount);
			book.setStock(currentStock);
			order.getOrderDetails().add(new OrderDetails(quantities.get(quantityCount++), order, book));
		});
		this.orderRepository.save(order);

		return order;
	}

	@Override
	public List<Order> readAllOrder() throws NoEntitiesException {

		List<Order> orders = this.orderRepository.findAll();
		if (orders.size() > 0)
			return orders;
		throw new NoEntitiesException("No orders exists till now");
	}

	@Override
	public Order readOrderById(String orderId) throws EntityNotFoundException {
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new EntityNotFoundException("No order found with ID: " + orderId));

		return order;
	}

}
