package book.commerce.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.commerce.test.entity.Address;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.service.AddressService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/address")
public class AddrressController {

	@Autowired
	private AddressService addressService;

	public ResponseEntity<?> createAddress(String customerId, Address address) throws EntityNotFoundException {
		Address createdAddress = this.addressService.createAddress(customerId, address);
		return ResponseEntity.status(201).body(createdAddress);

	}

	public ResponseEntity<?> readAllAddresses() throws NoEntitiesException {
		List<Address> addresses = this.addressService.readAllAddress();
		return ResponseEntity.status(200).body(addresses);
	}

	public ResponseEntity<?> readAddressById(String addressId) throws EntityNotFoundException {
		Address address = this.addressService.readAddressById(addressId);
		return ResponseEntity.status(201).body(address);
	}

	public ResponseEntity<?> updateAddressById(String addressId, Address address) throws EntityNotFoundException {
		Address updatedAddress = this.addressService.updateAddressById(addressId, address);
		return ResponseEntity.status(201).body(updatedAddress);

	}

	public ResponseEntity<?> deleteAddressById(String addressId) throws EntityNotFoundException {
		String message = this.addressService.deleteAddressById(addressId);
		return ResponseEntity.status(200).body(message);
	}
}
