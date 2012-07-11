package it.polito.ai.gas.dao;

import it.polito.ai.gas.business.ProducerInfo;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Statistics;
import it.polito.ai.gas.business.User;

public class Detacher {
	
	public static User detach(User user)
	{
		if (user == null)
			return null;
		
		User detached = new User();
		detached.setUsername(user.getUsername());
		detached.setPassword(user.getPassword());
		detached.setRole(user.getRole());
		detached.setName(user.getName());
		detached.setSurname(user.getSurname());
		detached.setBirthDate(user.getBirthDate());

		detached.setStatistics(detach(user.getStatistics()));

		detached.setProducerInfo(detach(user.getProducerInfo()));

		return detached;
	}

	public static Product detach(Product product)
	{
		Product detached = new Product();
		detached.setId(product.getId());
		detached.setName(product.getName());
		detached.setCost(product.getCost());
		detached.setUnits(product.getUnits());
		detached.setProducer(product.getProducer());
		
		return detached;
	}
	
	private static Statistics detach(Statistics statistics) {
		Statistics detached = new Statistics();
		detached.setNBought(statistics.getNBought());
		
		return detached;
	}

	public static ProducerInfo detach(ProducerInfo producerInfo)
	{
		if (producerInfo == null)
			return null;
		
		ProducerInfo detached = new ProducerInfo();
		detached.setAddress(producerInfo.getAddress());
		detached.setCompanyName(producerInfo.getCompanyName());
		detached.setContact(producerInfo.getContact());
		detached.setDescription(producerInfo.getDescription());
		detached.setEmail(producerInfo.getEmail());
		detached.setFaxNumber(producerInfo.getFaxNumber());
		detached.setPaymentMode(producerInfo.getPaymentMode());
		detached.setPhoneNumber(producerInfo.getPhoneNumber());
		
		detached.setDelegate(detach(producerInfo.getDelegate()));
		
		return detached;
	}
}
