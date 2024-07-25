package shopping.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import shopping.dto.Cart;
import shopping.dto.Customer;
import shopping.dto.ProductsOfCustomer;

public class CustomerCurd {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gowri");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et =em.getTransaction();
	public void save(Customer c) {
		et.begin();
		em.persist(c);
		et.commit();
	}
	public Customer save1(Customer c) {
		et.begin();
		c=em.merge(c);
		et.commit();
		return c;
	}
	public Customer login(String email, String pwd) {
		Query q =em.createQuery("select a from Customer a where email=?1 and password =?2");
		q.setParameter(1, email);
		q.setParameter(2, pwd);
		Customer c =(Customer) q.getSingleResult();
		return c;
	}
	public Customer find(int id) {
		Customer c =em.find(Customer.class, id);
		return c;
	}
	public void remove(Customer c) {
		et.begin();
		em.remove(c);
		et.commit();
	}
	public Customer saveAll(Customer c, List<ProductsOfCustomer> l) {
		et.begin();
		c.setL(new Cart(c.getId(),l));
		em.merge(c);
		et.commit();
		return c;
	}
	
}
