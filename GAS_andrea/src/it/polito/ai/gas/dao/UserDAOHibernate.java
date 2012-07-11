package it.polito.ai.gas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.polito.ai.gas.business.User;

@Transactional
public class UserDAOHibernate implements UserDAO {

	@Autowired
	private Logger logger;
	
	@PersistenceContext
	private EntityManager em;
	
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
		logger.info("Adding User: "+user);
		
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
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return null;
		}
		
		User check = null;
		try {
			check = em.find(User.class, username);
		} catch(Exception e) {	}
		if (check == null)
		{
			logger.info("User '"+username+"' doesn't exist.");
			return null;
		}

		em.flush();

		return Detacher.detach(check);
	}
	
	public User login(String username, String password)
	{
		User user = getUserByUsername(username);
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

	@Override
	public List<User> getUsersByRole(int role) {
		
		if (em == null)
		{
			logger.error("Entity Manager not found!");
			return null;
		}
		
		List<User> result = new ArrayList<User>();
		Query query = em.createQuery("SELECT u FROM User u WHERE role = :role")
				.setParameter("role", role);
		@SuppressWarnings("unchecked")
		List<User> us = query.getResultList();
		for(User u : us)
			result.add(Detacher.detach(u));

		em.flush();

		return result;
	}
	
}
