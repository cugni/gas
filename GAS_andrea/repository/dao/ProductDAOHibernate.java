package it.polito.ai.gas.dao_old;

import java.util.ArrayList;
import java.util.List;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProductDAOHibernate implements ProductDAO {

	private EntityManager em = null;
	
	public ProductDAOHibernate(DAOFactoryHibernate daoFactoryHibernate) {
		em = daoFactoryHibernate.getEntityManager();
	}

	private Product detach(Product product)
	{
		Product detached = new Product();
		detached.setId(product.getId());
		detached.setName(product.getName());
		detached.setCost(product.getCost());
		detached.setUnits(product.getUnits());
		detached.setProducer(product.getProducer());
		
		return detached;
	}
	
	public int addProduct(Product product)
	{
		System.out.println("Adding Product: "+product);
		
		if (em == null)
			return -1;
		
		// Attacco riferimento a producer (User)
		User producer = em.find(User.class, product.getProducer().getUsername());
		if (producer == null)
			return -2;
		
		product.setProducer(producer);
		
		em.persist(product);
		em.flush();
		
		return product.getId();
	}
	
	public int deleteProduct(int id) {
		return 0;
	}
	
	public int updateProduct(Product product) {
		return 0;
	}
	
	public Product getProductById(int id) {
		return null;
	}

	public List<Product> getProductByProducer(User producer) {
		List<Product> result = new ArrayList<Product>();
		
		if (em== null)
			return null;
		if (producer == null)
			return null;
		
		Query query = em.createQuery("SELECT p FROM Product p WHERE producer = :name")
			.setParameter("name", producer);
		List<Product> ps = query.getResultList();
		for(Product p : ps)
			result.add(detach(p));
		
		em.flush();
		return result;
	}
	
}
