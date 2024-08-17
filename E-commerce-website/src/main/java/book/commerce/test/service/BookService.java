package book.commerce.test.service;

import java.util.List;

import book.commerce.test.entity.Book;
import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;

public interface BookService {

	public Book createBook(Book book) throws EntityExistsException;

	public Book readBookById(String bookId) throws EntityNotFoundException;

	public List<Book> readAllBook() throws NoEntitiesException;

	public Book updateBookById(String bookId, Book book) throws EntityNotFoundException;

	public String deleteBookById(String bookId) throws EntityNotFoundException;

}
