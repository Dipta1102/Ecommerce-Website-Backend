package book.commerce.test.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import book.commerce.test.entity.Customer;
import book.commerce.test.entity.User;
import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.repository.UserRepository;
import book.commerce.test.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws EntityExistsException {
		if (this.userRepository.findById(user.getUsername()).orElse(null) != null)
			throw new EntityExistsException("Username " + user.getUsername() + " is already registered");

		Customer customer = new Customer(null, null, null, null, null, user);
		user.setCustomer(customer);
		user.setUserRole("ROLE_" + user.getUserRole());
		User createdUser = this.userRepository.save(user);

		return createdUser;
	}

	@Override
	public List<User> readAllUser() throws NoEntitiesException {
		List<User> user = this.userRepository.findAll();
		if (user.size() > 0)
			return user;

		throw new NoEntitiesException("No user exists till now");
	}

	@Override
	public User readUserById(String username) throws EntityNotFoundException {
		User user = this.userRepository.findById(username)
				.orElseThrow(() -> new EntityNotFoundException("No User found with ID: " + username));

		return user;
	}

	@Override
	public String deleteUserById(String username) throws EntityNotFoundException {
		User user = this.readUserById(username);
		this.userRepository.deleteById(username);

		return "deactivated account of user: " + username;
	}

}
