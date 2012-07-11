package it.polito.ai.gas.test;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.dao.*;

public class DAOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Product product = new Product();
		product.setName("Porcodio");
		product.setCost(666);
		product.setUnits("Kg");
		//product.setProducer(new User());
		
		//DAOFactory.getDAOFactoryHibernate().getProductDAOHibernate().addProduct(product);
	}

}
