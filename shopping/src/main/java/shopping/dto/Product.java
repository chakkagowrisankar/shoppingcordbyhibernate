package shopping.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int numberOfProduct;
	@Column(nullable = false)
	private double price;
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", numberOfProduct=" + numberOfProduct + ", price=" + price
				+ "]\n";
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
	public Product(int id, String name, int numberOfProduct, double price) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfProduct = numberOfProduct;
		this.price = price;
	}
	public Product(String name, int numberOfProduct, double price) {
		super();
		this.name = name;
		this.numberOfProduct = numberOfProduct;
		this.price = price;
	}
	public Product() {
		super();
	} 
	
}
