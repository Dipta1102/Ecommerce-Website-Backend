package book.commerce.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/add")
	public ResponseEntity<?> createAddress(@RequestParam String customerId, @RequestBody Address address)
			throws EntityNotFoundException {
		Address createdAddress = this.addressService.createAddress(customerId, address);
		return ResponseEntity.status(201).body(createdAddress);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/fetch-all")
	public ResponseEntity<?> readAllAddresses() throws NoEntitiesException {
		List<Address> addresses = this.addressService.readAllAddress();
		return ResponseEntity.status(200).body(addresses);
	}

	@PreAuthorize("hasRole('USER,ADMIN')")
	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> readAddressById(@PathVariable("id") String addressId) throws EntityNotFoundException {
		Address address = this.addressService.readAddressById(addressId);
		return ResponseEntity.status(201).body(address);
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> updateAddressById(@PathVariable("id") String addressId, @RequestBody Address address)
			throws EntityNotFoundException {
		Address updatedAddress = this.addressService.updateAddressById(addressId, address);
		return ResponseEntity.status(201).body(updatedAddress);

	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAddressById(@PathVariable String addressId) throws EntityNotFoundException {
		String message = this.addressService.deleteAddressById(addressId);
		return ResponseEntity.status(200).body(message);
	}
}
