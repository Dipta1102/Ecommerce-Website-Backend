package book.commerce.test.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import book.commerce.test.entity.Author;
import book.commerce.test.entity.Book;
import book.commerce.test.exception.EntityExistsException;
import book.commerce.test.exception.EntityNotFoundException;
import book.commerce.test.exception.NoEntitiesException;
import book.commerce.test.repository.BookRepository;
import book.commerce.test.service.AuthorService;
import book.commerce.test.service.BookService;

public class BookServiceImpl implements BookService {

	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book createBook(Book book) throws EntityExistsException {
		List<Author> givenAuthors = book.getAuthors();
		for (int i = 0; i < givenAuthors.size(); i++) {
			try {
				String givenAuthorId = book.getAuthors().get(i).getAuthorId();
				givenAuthorId = (givenAuthorId == null) ? "0" : givenAuthorId;
				Author savedAuthor = this.authorService.readAuthorById(givenAuthorId);
				book.getAuthors().set(i, savedAuthor);
			} catch (EntityNotFoundException e) {
				System.out.println(e);
				continue;
			}

		}
		List<Author> authors = book.getAuthors();
		authors.forEach(author -> author.getBooks().add(book));
		Book createdBook = this.bookRepository.save(book);
		return createdBook;
	}

	@Override
	public Book readBookById(String bookId) throws EntityNotFoundException {
		Book book = this.bookRepository.findById(bookId)
				.orElseThrow(() -> new EntityNotFoundException("No book found with this Id: " + bookId));

		return book;
	}

	@Override
	public List<Book> readAllBook() throws NoEntitiesException {
		List<Book> books = this.bookRepository.findAll();
		if (books.size() > 0)
			return books;

		throw new NoEntitiesException("No book exists till now");
	}

	@Override
	public Book updateBookById(String bookId, Book book) throws EntityNotFoundException {
		Book updatedBook = this.readBookById(bookId);
		updatedBook.setBookName(book.getBookName());
		updatedBook.setPageCount(book.getPageCount());
		updatedBook.setPrice(book.getPrice());
		updatedBook.setStock(book.getStock());
		updatedBook.setBookImage(book.getBookImage());
		this.bookRepository.save(updatedBook);
		return updatedBook;
	}

	@Override
	public String deleteBookById(String bookId) throws EntityNotFoundException {
		Book book = this.readBookById(bookId);
		this.bookRepository.deleteById(bookId);
		return "Deleted book" + book.getBookId() + " " + book.getBookName();
	}

}
