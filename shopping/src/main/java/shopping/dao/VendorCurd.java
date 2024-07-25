package shopping.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import shopping.dto.Vendor;

public class VendorCurd {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gowri");
	EntityManager em= emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	public void save(Vendor v) {
		et.begin();
		em.persist(v);
		et.commit();
	}
	public Vendor save1(Vendor v) {
		et.begin();
		em.merge(v);
		et.commit();
		return v;
	}
	public Vendor login(String e,String pwd) {
		Query q =em.createQuery("select a from Vendor a where email=?1 and password=?2");
		q.setParameter(1, e);
		q.setParameter(2, pwd);
		Vendor v =(Vendor) q.getSingleResult();
		return v;
	}
	public Vendor find(int id) {
		Vendor v =em.find(Vendor.class, id);
		return v;
	}
	
	public void delet(Vendor vendor) {
		et.begin();
		em.remove(vendor);
		et.commit();
	}
	
	
	
	
}
