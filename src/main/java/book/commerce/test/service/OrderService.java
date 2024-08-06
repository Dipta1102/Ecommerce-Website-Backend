package book.commerce.test.service;

import java.util.List;

import book.commerce.test.entity.Order;
import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;

public interface OrderService {

	public Order createOrder(String addressId, List<List<String>> orders)
			throws EntityExistsException, EntityNotFoundException;

	public List<Order> readAllOrder() throws NoEntitiesException;

	public Order readOrderById(String orderId) throws EntityNotFoundException;
}
