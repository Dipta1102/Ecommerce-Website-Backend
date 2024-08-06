package book.commerce.test.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.commerce.test.entity.Address;
import book.commerce.test.entity.Customer;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.repository.AddressRepository;
import book.commerce.test.repository.CustomerRepository;
import book.commerce.test.service.AddressService;
import book.commerce.test.service.CustomerService;

@Service
public class AddressServiceImpl implements AddressService {

//	public AddressServiceImpl(CustomerService customerService) {
//		super();
//		this.customerService = customerService;
//	}

	@Autowired
	private CustomerService customerService;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Address createAddress(String customerId, Address address) throws EntityNotFoundException {
		Customer customer = this.customerService.readCustomerById(customerId);
		address.setCustomer(customer);
		customer.getAddresses().add(address);
		List<Address> addresses = this.customerRepository.save(customer).getAddresses();
		Address createdAddress = addresses.get(addresses.size() - 1);

		return createdAddress;
	}

	@Override
	public List<Address> readAllAddress() throws NoEntitiesException {
		List<Address> addresses = this.addressRepository.findAll();
		if (addresses.size() > 0)
			return addresses;

		throw new NoEntitiesException("No addresses exists till now");
	}

	@Override
	public Address readAddressById(String addressId) throws EntityNotFoundException {
		Address address = this.addressRepository.findById(addressId)
				.orElseThrow(() -> new EntityNotFoundException("No address found with ID: " + addressId));

		return address;
	}

	@Override
	public Address updateAddressById(String addressId, Address address) throws EntityNotFoundException {
		Address updatedAddress = this.readAddressById(addressId);
		updatedAddress.setName(address.getName());
		updatedAddress.setState(address.getState());
		updatedAddress.setCity(address.getCity());
		updatedAddress.setLocality(address.getLocality());
		updatedAddress.setAddressLine(address.getAddressLine());
		updatedAddress.setLandmark(address.getLandmark());
		updatedAddress.setPincode(address.getPincode());
		updatedAddress.setPhone(address.getPhone());
		updatedAddress.setAlternate_Phone(address.getAlternate_Phone());
		this.addressRepository.save(updatedAddress);
		return updatedAddress;
	}

	@Override
	public String deleteAddressById(String addressId) throws EntityNotFoundException {
		Address address = this.readAddressById(addressId);
		this.addressRepository.deleteById(addressId);
		String userName = this.customerService.readCustomerById(address.getCustomer().getCustomerId()).getUser()
				.getUsername();
		return "Deleted addresses of user " + userName;
	}

}
