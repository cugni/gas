package it.polito.ai.gas.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.polito.ai.gas.business.ProducerInfo;
import it.polito.ai.gas.business.User;

@Transactional
public class ProducerInfoDAOHibernate implements ProducerInfoDAO {

	@Autowired
	private Logger logger;
	
	@PersistenceContext
	private EntityManager em;

	
	public int addProducerInfo(ProducerInfo producerInfo) {

		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return -1;
		}
		
		if (producerInfo.getDelegate() == null)
		{
			logger.error("Delegate can't be null.");			
			return -2;
		}		
		User delegate = em.find(User.class, producerInfo.getDelegate().getUsername());
		if (delegate == null)
		{
			logger.error("Delegate can't be found.");			
			return -2;
		}
		
		if (delegate.getRole() != User.getRoleNumber("delegate"))
		{
			logger.error("User '"+ delegate.getUsername() +"' is not a delegate. " +
					"(Role = "+ User.getRoleName(delegate.getRole()) +").");
			return -3;
		}
		
		producerInfo.setDelegate(delegate);
		
		logger.info("Adding ProducerInfo: "+ producerInfo);
		
		em.persist(producerInfo);
		em.flush();
		
		return producerInfo.getId();
	}

	public int deleteProducerInfo(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateProducerInfo(ProducerInfo producerInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
