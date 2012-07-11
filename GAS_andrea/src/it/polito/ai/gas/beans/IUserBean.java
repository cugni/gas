package it.polito.ai.gas.beans;

import it.polito.ai.gas.business.Product;

import java.util.List;


public interface IUserBean {

	public abstract String getUsername();

	public abstract void setUsername(String username);

	public abstract int getRole();

	public abstract void setRole(int role);
	
	public abstract List<Product> getProducts();


}