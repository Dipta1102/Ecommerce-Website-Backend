package book.commerce.test.service;

import java.util.List;

import book.commerce.test.entity.Customer;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;

public interface CustomerService {

	public List<Customer> readAllCustomer() throws NoEntitiesException;

	public Customer readCustomerById(String customerId) throws EntityNotFoundException;

	public Customer updateCustomerById(String customerId, Customer customer) throws EntityNotFoundException;
}