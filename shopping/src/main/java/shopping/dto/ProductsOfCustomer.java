package shopping.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductsOfCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int numberOfProduct;
	@Column(nullable = false)
	private double price;

	public int getId() {
		return id;
	}

	public ProductsOfCustomer(String name, int numberOfProduct, double price, int idOfP) {
		super();
		this.name = name;
		this.numberOfProduct = numberOfProduct;
		this.price = price;
		this.idOfP = idOfP;
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

	public int getNumberOfProduct() {
		return numberOfProduct;
	}

	public void setNumberOfProduct(int numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdOfP() {
		return idOfP;
	}

	public void setIdOfP(int idOfP) {
		this.idOfP = idOfP;
	}

	private int idOfP;
	@Override
	public String toString() {
		return "ProductsOfCustomer [id=" + id + ", name=" + name + ", numberOfProduct=" + numberOfProduct + ", price="
				+ price + "]\n";
	}

	public ProductsOfCustomer(int id, String name, int numberOfProduct, double price) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfProduct = numberOfProduct;
		this.price = price;
	}

	public ProductsOfCustomer(String name, int numberOfProduct, double price) {
		super();
		this.name = name;
		this.numberOfProduct = numberOfProduct;
		this.price = price;
	}

	public ProductsOfCustomer() {
		super();
	}
	

}
