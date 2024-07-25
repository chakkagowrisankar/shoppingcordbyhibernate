package shopping.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import shopping.dto.Cart;
import shopping.dto.Customer;
import shopping.dto.Product;
import shopping.dto.ProductsOfCustomer;


public class ProductCurd {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gowri");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public List<Product> fatchAll() {
		Query q = em.createQuery("select a from Product a ");
		List<Product> l = q.getResultList();
		return l;
	}

	public Product find(int id) {
		Product p = em.find(Product.class, id);
		return p;
	}

	public void save(Product p) {
		et.begin();
		em.merge(p);
		et.commit();

	}

	public void modify(int id1, int numberOfProduct) {
		Product p = em.find(Product.class, id1);
		System.out.println(p);
		p.setNumberOfProduct(p.getNumberOfProduct() - numberOfProduct);
		et.begin();
		em.merge(p);
		et.commit();
	}
//	public void remove(Customer c) {
//		et.begin();
//		List<ProductOfCustomer> l=c.getL().getL();
//		c.setL(new Cart(c.getId(),null));
//		em.merge(c);
//		et.commit();
//		r(l);
//	}
//	public void r(List<ProductOfCustomer> l) {
//		for(ProductOfCustomer p:l) {
//			et.begin();
//			em.remove(p);
//			et.commit();
//		}
//	}
//}

	public void remove(Customer c) {
		Cart cart = c.getL();
		List<ProductsOfCustomer> p = cart.getP();
		et.begin();
		cart.setL(null);
		em.merge(cart);

		for (ProductsOfCustomer pro : p) {
			ProductsOfCustomer product = em.find(ProductsOfCustomer.class, pro.getId());
			em.remove(product);

		}
		

		et.commit();

	}

	public Customer removeonly(Customer c, int item) {
		ProductsOfCustomer product = em.find(ProductsOfCustomer.class, item);
		et.begin();
		Cart cart = c.getL();
		cart.getP().remove(product);
		c.setL(cart);
		c=em.merge(c);
		em.remove(product);
		et.commit();
		return c;
		
	}
	public ProductsOfCustomer find1(int i) {
		ProductsOfCustomer product = em.find(ProductsOfCustomer.class, i);
		return product;
	}
}
