package it.polito.ai.gas.dao;

import java.util.List;

import it.polito.ai.gas.business.Product;

public interface ProductDAO {

	public int addProduct(Product product);
	
	public int deleteProduct(int id);
	
	public int updateProduct(Product product);
	
	public Product getProductById(int id);
	
	public List<Product> getProductsByProducer(String producerName);
	
}
