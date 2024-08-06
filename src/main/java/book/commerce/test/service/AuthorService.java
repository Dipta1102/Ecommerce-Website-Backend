package book.commerce.test.service;

import java.util.List;

import book.commerce.test.entity.Author;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;

public interface AuthorService {
	public List<Author> readAllAuthor() throws NoEntitiesException;

	public Author readAuthorById(String authorId) throws EntityNotFoundException;

	public Author readAuthorByEmail(String authorEmail) throws EntityNotFoundException;

}
