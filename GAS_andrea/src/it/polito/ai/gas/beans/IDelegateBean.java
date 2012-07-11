package it.polito.ai.gas.beans;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.User;

import java.util.List;

public interface IDelegateBean {
	
	public abstract List<User> getProducers();
	
	public abstract List<Product> getProductsByProducer(User producer);

}
