package book.commerce.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@GenericGenerator(name = "order_details_id_generator", strategy = "book.commerce.test.generator.OrderDetailsIdGenerator")
	@GeneratedValue(generator = "order_details_id_generator")
	@Column(name = "details_id")
	private String detailsId;

	@Column(name = "quantity")
	private long quantity;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	public OrderDetails(long quantity, Order order, Book book) {
		super();
		this.quantity = quantity;
		this.order = order;
		this.book = book;
	}

}
