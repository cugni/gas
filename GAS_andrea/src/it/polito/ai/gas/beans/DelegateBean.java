package it.polito.ai.gas.beans;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.dao.ProductDAO;
import it.polito.ai.gas.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class DelegateBean implements IDelegateBean {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private IUserBean userBean;
	
	@Override
	public List<User> getProducers() {
		List<User> result = new ArrayList<User>();
		List<User> producers = userDAO.getUsersByRole(User.getRoleNumber("producer"));
		for(User p : producers)
			if (p.getProducerInfo().getDelegate().getUsername().equals(userBean.getUsername()))
				result.add(p);
		
		return result;
	}

	@Override
	public List<Product> getProductsByProducer(User producer) {
		return productDAO.getProductsByProducer(producer.getUsername());
	}


}
