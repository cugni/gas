package it.polito.ai.gas.dao_old;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.polito.ai.gas.business.User;

public class UserDAOHibernate implements UserDAO {

	private Logger logger = Logger.getRootLogger();
	
	private EntityManager em;
	
	public UserDAOHibernate(DAOFactoryHibernate daoFactoryHibernate) {
		em = daoFactoryHibernate.getEntityManager();
	}
	
	private User detach(User user)
	{
		User detached = new User();
		detached.setName(user.getName());
		detached.setPassword(user.getPassword());
		detached.setRole(user.getRole());
		
		return detached;
	}
	
	private String encrypt(String source) {
		String result = null;
		
		/* MD5...
		private MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			byte[] b = source.getBytes("UTF-8");
			byte[] b_digest = md.digest(b);
			result = b_digest.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/
		
		result = ""+source.hashCode();
		return result;
	}
	
	public String addUser(User user) {
		System.out.println("Adding User: "+user);
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return null;
		}
		
		if (getUserByUsername(user.getUsername()) != null)
		{
			logger.error("User '"+user.getUsername()+"' already exists.");
			return null;
		}
		// Cifro password
		user.setPassword(encrypt(user.getPassword()));
		
		em.persist(user);
		em.flush();
		
		return user.getName();
	}
	
	
	public boolean deleteUser(String username) {
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return false;
		}
		
		User user = getUserByUsername(username);
		if (user == null)
		{
			logger.error("Can't delete a non-existent user.");
			return false;
		}
		
		em.remove(user);
		em.flush();
		
		return true;		
	}
	
	public int updateUser(User user) {
		return 0;
	}
	
	public User getUserByUsername(String username) {
		User result = null;
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return null;
		}
		
		try {
			User user = em.find(User.class, username);
			result = detach(user);
		} catch(Exception e) {	}
		if (result == null)
			logger.error("User '"+username+"' doesn't exist.");
		
		em.flush();

		return result;
	}
	
	public User login(String name, String password)
	{
		User user = getUserByUsername(name);
		if (user == null)
			return null;
		
		String pw_user = null, pw_check = null;

		pw_user = user.getPassword();
		pw_check = encrypt(password);
		if (!pw_user.equals(pw_check))
		{
			logger.error("Password mismatch.");
			return null;
		}
		
		return user;
	}
	
}
