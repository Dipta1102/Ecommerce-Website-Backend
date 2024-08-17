package book.commerce.test.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "DemoBooks")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private String bookId;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "page_count")
	private long pageCount;

	@Column(name = "price")
	private double price;

	@Column(name = "stock")
	private long stock;

	@Column(name = "book_image")
	private String bookImage;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	private List<OrderDetails> orderDetails = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "book_author", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private List<Author> authors = new ArrayList<>();

	public Book(String bookName, long pageCount, double price, long stock, String bookImage) {
		super();
		this.bookName = bookName;
		this.pageCount = pageCount;
		this.price = price;
		this.stock = stock;
		this.bookImage = bookImage;
	}

}
