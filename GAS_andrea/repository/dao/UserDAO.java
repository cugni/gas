package it.polito.ai.gas.dao_old;

import it.polito.ai.gas.business.User;

public interface UserDAO {

	public String addUser(User user);
	
	public boolean deleteUser(String username);
	
	public int updateUser(User user);
	
	public User getUserByUsername(String name);
	
	public User login(String name, String password);
	
}
