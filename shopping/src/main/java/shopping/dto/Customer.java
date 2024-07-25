package shopping.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false,unique = true)
	private long phoneNumber;
	@Column(nullable = false)
	private String gender;
	@OneToOne(cascade = CascadeType.ALL)
	private Cart c;
	
	public Customer(String name, String email, String password, long phoneNumber, String gender, Cart c) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.c = c;
	}
	public Customer(int id, String name, String email, String password, long phoneNumber, String gender, Cart c) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.c = c;
	}
	public Cart getL() {
		return c;
	}
	public void setL(Cart c) {
		this.c = c;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", Card=" + c + "]\n";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Customer(int id, String name, String email, String password, long phoneNumber, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	public Customer(String name, String email, String password, long phoneNumber, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	public Customer() {
		super();
	}
	
}
