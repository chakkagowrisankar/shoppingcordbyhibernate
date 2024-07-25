package shopping.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String address;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Product> p;
	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", address=" + address + ", p=" + p + "]\n";
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public List<Product> getP() {
		return p;
	}
	public void setP(List<Product> p) {
		this.p = p;
	}
	public Shop(int id, String name, String address, List<Product> p) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.p = p;
	}
	public Shop(String name, String address, List<Product> p) {
		super();
		this.name = name;
		this.address = address;
		this.p = p;
	}
	public Shop(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public Shop() {
		super();
	}
	
}
