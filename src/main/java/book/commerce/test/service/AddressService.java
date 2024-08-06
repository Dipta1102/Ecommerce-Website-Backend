package book.commerce.test.service;

import java.util.List;

import book.commerce.test.entity.Address;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;

public interface AddressService {

	public Address createAddress(String customerId, Address address) throws EntityNotFoundException;

	public List<Address> readAllAddress() throws NoEntitiesException;

	public Address readAddressById(String addressId) throws EntityNotFoundException;

	public Address updateAddressById(String addressId, Address address) throws EntityNotFoundException;

	public String deleteAddressById(String addressId) throws EntityNotFoundException;

}
