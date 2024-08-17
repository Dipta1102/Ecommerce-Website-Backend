package book.commerce.test.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "author_id")
	private String authorId;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "author_email")
	private String authorEmail;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "author")
	private List<Book> books = new ArrayList<>();

	public Author(String authorName, String authorEmail) {
		super();
		this.authorName = authorName;
		this.authorEmail = authorEmail;
	}

}
