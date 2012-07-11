package it.polito.ai.gas.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.polito.ai.gas.business.Approval;
import it.polito.ai.gas.business.User;

@Transactional
public class ApprovalDAOHibernate implements ApprovalDAO {

	@Autowired
	private Logger logger;
	
	@PersistenceContext
	private EntityManager em;
	
	public int addApproval(Approval approval) {
		logger.info("Adding approval: "+approval);

		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return -1;
		}
		
		User user = approval.getUser();
		if (user == null)
		{
			logger.error("User can't be null.");
			return -2;
		}

		em.persist(approval);
		em.flush();
		
		return approval.getId();
	}

	public int approve(int id, String admin) {
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return -1;
		}
		
		Approval approval = em.find(Approval.class, id);
		if (approval == null)
		{
			logger.error("Approval ['"+ id +"'] not found.");
			return -2;
		}
		User user_admin = em.find(User.class, admin);
		if (user_admin == null)
		{
			logger.error("Admin '"+ admin +"' not found.");
			return -3;
		}
		
		approval.setApproved(true);
		approval.setAdmin(user_admin);
		
		em.persist(approval);
		em.flush();
		
		return approval.getId();
	}

}
