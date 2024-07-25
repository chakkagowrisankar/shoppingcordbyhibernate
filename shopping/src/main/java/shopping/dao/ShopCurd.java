package shopping.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import shopping.dto.Shop;
import shopping.dto.Vendor;

public class ShopCurd {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gowri");
	EntityManager em =emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	public Vendor save(Vendor v) {
		et.begin();
		v=em.merge(v);
		et.commit();
		return v;
	}
	public void remove(Vendor v, int id) {
		Shop s = em.find(Shop.class, id);
		et.begin();
		List <Shop>l=v.getL();
		l.remove(s);
		v.setL(l);
		em.remove(s);
		et.commit();
	}
	public void update(Vendor v) {
		et.begin();
		em.merge(v);
		et.commit();
	}
	public Shop find(int id) {
		Shop s=em.find(Shop.class, id);
		return s;
	}
	public Vendor saveShop(Shop s,int id) {
		et.begin();
		em.merge(s);
		et.commit();
		Vendor v =em.find(Vendor.class, id);
		return v;
	}
	
}
