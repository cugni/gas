package it.polito.ai.gas.beans;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.dao.ProductDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UserBean implements IUserBean {

	@Autowired
	private ProductDAO productDAO;
	
	private String username;
	private int role;
	
	@Override
	public List<Product> getProducts()
	{
		if (getRole() != User.getRoleNumber("producer"))
			return null;
		
		return productDAO.getProductsByProducer(getUsername());
	}
	
	
	// --- Getters & Setters ---
	
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public int getRole() {
		return role;
	}
	@Override
	public void setRole(int role) {
		this.role = role;
	}

}
