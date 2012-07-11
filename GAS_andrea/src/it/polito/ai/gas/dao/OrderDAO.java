package it.polito.ai.gas.dao;

import java.util.List;

import it.polito.ai.gas.business.Order;

public interface OrderDAO {

	public int addOrder(Order order);
	
	public int deleteProduct(int id);
	
	public int updateProduct(Order order);
	
	public Order getOrderById(int id);
	
	public List<Order> getOrdersByProduct(String productName);
	
}
