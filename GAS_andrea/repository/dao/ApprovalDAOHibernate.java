package it.polito.ai.gas.dao_old;

import javax.persistence.EntityManager;

import it.polito.ai.gas.business.Approval;
import it.polito.ai.gas.business.User;

public class ApprovalDAOHibernate implements ApprovalDAO {

	private EntityManager em = null;

	public ApprovalDAOHibernate(DAOFactoryHibernate daoFactoryHibernate) {
		em = daoFactoryHibernate.getEntityManager();
	}
	
	public int addApproval(Approval approval) {
		
		if (em == null)
			return -1;
		
		User user = approval.getUser();
		if (user == null)
			return -2;

		em.persist(approval);
		em.flush();
		
		return approval.getId();
	}

	public int approve(int id, String admin) {
		
		if (em == null)
			return -1;
		
		Approval approval = em.find(Approval.class, id);
		if (approval == null)
			return -2;
		User user_admin = em.find(User.class, admin);
		if (user_admin == null)
			return -3;
		
		approval.setApproved(true);
		approval.setAdmin(user_admin);
		
		em.persist(approval);
		em.flush();
		
		return approval.getId();
	}

}
