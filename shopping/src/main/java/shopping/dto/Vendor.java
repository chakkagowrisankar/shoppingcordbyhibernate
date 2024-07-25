package shopping.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vendor {
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
	@OneToMany(cascade = CascadeType.ALL)
	private List<Shop> l;
	
	public Vendor(String name, String email, String password, long phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	public Vendor(String name, String email, String password, long phoneNumber, List<Shop> l) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.l = l;
	}
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", l=" + l + "]\n";
	}
	public Vendor() {
		super();
	}
	public Vendor(int id, String name, String email, String password, long phoneNumber, List<Shop> l) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.l = l;
	}
	
	public List<Shop> getL() {
		return l;
	}
	public void setL(List<Shop> l) {
		this.l = l;
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
	
	
}
