package it.polito.ai.gas.dao_old;

import javax.persistence.*;

public class DAOFactoryHibernate {
	
	// This step will read hibernate.cfg.xml and prepare hibernate for use
	private static EntityManagerFactory entityManagerFactory = null;
	static {
		try {
	        entityManagerFactory = Persistence.createEntityManagerFactory("GAS");
	    } 
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	// Every instance of the factory owns its own session; this avoid the session per operation anti-pattern
	// and enables the session per-whole-server-request one
	private EntityManager entityManager = null;
	private EntityTransaction transaction = null;
	
	public DAOFactoryHibernate()
	{
		 this.entityManager = entityManagerFactory.createEntityManager();
		 this.transaction = entityManager.getTransaction();
		 this.transaction.begin();
	}
	
	protected void terminate() {
		// if something bad happens in hibernate, it closes the current session by itself!
		if ( entityManager != null && entityManager.isOpen() ) {
			entityManager.close();
		}
	}
	
	public EntityManager getEntityManager() {
		 return entityManager;
	}
	
	
	// --- DAO ---
	
	public ProductDAOHibernate getProductDAOHibernate() {
		return new ProductDAOHibernate(this);
	}
	
	public UserDAOHibernate getUserDAOHibernate() {
		return new UserDAOHibernate(this);
	}
	
	public ApprovalDAOHibernate getApprovalDAOHibernate() {
		return new ApprovalDAOHibernate(this);
	}
	
	public ProducerInfoDAOHibernate getProducerInfoDAOHibernate() {
		return new ProducerInfoDAOHibernate(this);
	}
}
