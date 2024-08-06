package book.commerce.test.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import book.commerce.test.entity.Customer;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.repository.CustomerRepository;
import book.commerce.test.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> readAllCustomer() throws NoEntitiesException {
		List<Customer> customer = this.customerRepository.findAll();
		if (customer.size() > 0)
			return customer;

		throw new NoEntitiesException("No customer exists till now");

	}

	@Override
	public Customer readCustomerById(String customerId) throws EntityNotFoundException {
		Customer customer = this.customerRepository.findById(customerId)
				.orElseThrow(() -> new EntityNotFoundException("No Customer found with ID: " + customerId));

		return customer;
	}

	@Override
	public Customer updateCustomerById(String customerId, Customer customer) throws EntityNotFoundException {
		// firstname,lastname,gender,phone,email

		Customer updatedCustomer = this.readCustomerById(customerId);
		updatedCustomer.setFirstName(customer.getFirstName());
		updatedCustomer.setLastName(customer.getLastName());
		updatedCustomer.setGender(customer.getGender());
		updatedCustomer.setPhone(customer.getPhone());
		updatedCustomer.setEmail(customer.getEmail());

		this.customerRepository.save(updatedCustomer);
		return updatedCustomer;

	}

}
