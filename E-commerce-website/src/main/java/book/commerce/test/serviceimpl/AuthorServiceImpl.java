package book.commerce.test.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import book.commerce.test.entity.Author;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.repository.AuthorRepository;
import book.commerce.test.service.AuthorService;

public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> readAllAuthor() throws NoEntitiesException {
		List<Author> authors = this.authorRepository.findAll();
		if (authors.size() > 0)
			return authors;

		throw new NoEntitiesException("No Author Exist till now");
	}

	@Override
	public Author readAuthorById(String authorId) throws EntityNotFoundException {
		Author author = this.authorRepository.findById(authorId)
				.orElseThrow(() -> new EntityNotFoundException("No author found by ID: " + authorId));
		return author;
	}

	@Override
	public Author readAuthorByEmail(String authorEmail) throws EntityNotFoundException {
		Author author = this.authorRepository.findById(authorEmail)
				.orElseThrow(() -> new EntityNotFoundException("No author found by Email: " + authorEmail));
		return author;
	}

}
