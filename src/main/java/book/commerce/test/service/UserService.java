package book.commerce.test.service;

import java.util.List;

import book.commerce.test.entity.User;
import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;

public interface UserService {

	public User createUser(User user) throws EntityExistsException;

	public List<User> readAllUser() throws NoEntitiesException;

	public User readUserById(String username) throws EntityNotFoundException;

	public String deleteUserById(String username) throws EntityNotFoundException;
}
