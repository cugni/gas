package it.polito.ai.gas.dao_old;

import javax.persistence.EntityManager;

import it.polito.ai.gas.business.ProducerInfo;
import it.polito.ai.gas.business.User;

public class ProducerInfoDAOHibernate implements ProducerInfoDAO {

	private EntityManager em = null;
	
	public ProducerInfoDAOHibernate(DAOFactoryHibernate daoFactoryHibernate) {
		em = daoFactoryHibernate.getEntityManager();
	}
	
	public int addProducerInfo(ProducerInfo producerInfo) {

		if (em == null)
			return -1;
		
		User delegate = em.find(User.class, producerInfo.getDelegate().getUsername());
		if (delegate == null)
			return -2;
		if (delegate.getRole() != User.getRoleNumber("delegate"))
			return -3;
		
		producerInfo.setDelegate(delegate);
		
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
