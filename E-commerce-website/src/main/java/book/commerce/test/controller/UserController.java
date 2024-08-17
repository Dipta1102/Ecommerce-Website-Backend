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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.commerce.test.entity.User;
import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody User user) throws EntityNotFoundException, EntityExistsException {
		User createdUser = this.userService.createUser(user);
		return ResponseEntity.status(201).body(createdUser);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/fetch-all")
	public ResponseEntity<?> readAllUser() throws NoEntitiesException {
		List<User> users = this.userService.readAllUser();
		return ResponseEntity.status(200).body(users);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/fetch/{username}")
	public ResponseEntity<?> readUserById(@PathVariable("username") String username) throws EntityNotFoundException {
		User user = this.userService.readUserById(username);
		return ResponseEntity.status(200).body(user);
	}

	@PreAuthorize("hasRole('ADMIN','USER')")
	@DeleteMapping("/deactivate-account/{username}")
	public ResponseEntity<?> deleteUserById(@PathVariable("username") String username) throws EntityNotFoundException {
		String message = this.userService.deleteUserById(username);
		return ResponseEntity.status(200).body(message);
	}

}
