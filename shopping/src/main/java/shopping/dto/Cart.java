package shopping.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ProductsOfCustomer> p;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ProductsOfCustomer> getP() {
		return p;
	}
	public void setL(List<ProductsOfCustomer> P) {
		this.p = P;
	}
	@Override
	public String toString() {
		return "Cart [l=" + p + "]\n";
	}
	public Cart(int id, List<ProductsOfCustomer> l) {
		super();
		this.id = id;
		this.p = l;
	}
	public Cart() {
		super();
	}
	public Cart(List<ProductsOfCustomer> l) {
		super();
		this.p = l;
	} 
	
}
