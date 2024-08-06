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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private String addressId;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "locality")
	private String locality;

	@Column(name = "address_line")
	private String addressLine;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "phone")
	private String phone;

	@Column(name = "alternate_phone")
	private String alternate_Phone;

	@ManyToOne
	@JoinColumn(name = "customer_Id")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
	private List<Order> orders = new ArrayList<>();

	public Address(String name, String state, String locality, String addressLine, String landmark, String pincode,
			String phone, String alternate_Phone, Customer customer) {
		super();
		this.name = name;
		this.state = state;
		this.locality = locality;
		this.addressLine = addressLine;
		this.landmark = landmark;
		this.pincode = pincode;
		this.phone = phone;
		this.alternate_Phone = alternate_Phone;
		this.customer = customer;
	}

}
