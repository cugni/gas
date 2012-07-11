package it.polito.ai.gas.dao;

import java.util.ArrayList;
import java.util.List;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProductDAOHibernate implements ProductDAO {

	@Autowired
	private Logger logger;
	@Autowired
	private UserDAO userDAO;
	
	@PersistenceContext
	private EntityManager em;
	
	public int addProduct(Product product)
	{
		logger.info("Adding Product: "+product);
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return -1;
		}
		
		// Attacco riferimento a producer (User)
		if (product.getProducer() == null)
		{
			logger.error("Producer can't be null.");
			return -2;
		}
		User producer = em.find(User.class, product.getProducer().getUsername());
		if (producer == null)
		{
			logger.error("Producer can't be found.");
			return -2;
		}
		
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

	public List<Product> getProductsByProducer(String producerName) {
		List<Product> result = new ArrayList<Product>();
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return null;
		}
		if (producerName == null)
		{
			logger.error("Producer name can't be null.");
			return null;
		}
		
		logger.info("Getting products with producer = "+ producerName);
		
		User producer = userDAO.getUserByUsername(producerName);
		if (producer == null)
		{
			logger.error("Producer can't be found.");
			return null;
		}		
		
		Query query = em.createQuery("SELECT p FROM Product p WHERE producer = :name")
			.setParameter("name", producer);
		@SuppressWarnings("unchecked")
		List<Product> ps = query.getResultList();
		for(Product p : ps)
			result.add(Detacher.detach(p));
		
		em.flush();
		return result;
	}
	
}
