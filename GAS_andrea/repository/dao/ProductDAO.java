package it.polito.ai.gas.dao_old;

import java.util.List;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.User;

public interface ProductDAO {

	public int addProduct(Product product);
	
	public int deleteProduct(int id);
	
	public int updateProduct(Product product);
	
	public Product getProductById(int id);
	
	public List<Product> getProductByProducer(User producer);
	
}
