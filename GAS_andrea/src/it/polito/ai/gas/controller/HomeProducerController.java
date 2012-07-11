package it.polito.ai.gas.controller;

import javax.validation.Valid;

import it.polito.ai.gas.beans.IUserBean;
import it.polito.ai.gas.beans.UserBean;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.dao.ProductDAO;
import it.polito.ai.gas.dao.UserDAO;
import it.polito.ai.gas.forms.AddProductForm;
import it.polito.ai.gas.forms.LogoutForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("user")
@Controller
public class HomeProducerController {

	@Autowired
	private Logger logger;
	@Autowired
	private IUserBean userBean;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/home_producer")
	public ModelAndView View(LogoutForm logoutForm, AddProductForm addProductForm) {
		ModelAndView mv = new ModelAndView("home_producer");
		mv.addObject("logout", logoutForm);
		mv.addObject("addProduct", addProductForm);
		return mv;
	}
	
	@RequestMapping(value = "/home_producer", method = RequestMethod.POST)
	public ModelAndView processForm(
			@ModelAttribute("logout") LogoutForm logoutForm,
			@ModelAttribute("addProduct") @Valid AddProductForm addProductForm,
			BindingResult addProductResult)
	{
		
		ModelAndView success = null; //dipende dall'azione
		ModelAndView failure = new ModelAndView("/home_producer");
		
		/* --- LOGOUT --- */
		if (logoutForm.getCommand().equals("logout"))
		{	
			success = new ModelAndView("redirect:/pages/welcome");

			success.addObject("user", new UserBean());
	
			logger.info("USER LOGGED OUT.");	
		}
		
		/* --- ADD PRODUCT --- */
		if (addProductForm.getCommand().equals("addProduct"))
		{
			success = new ModelAndView("/home_producer"); 

			if (addProductResult.hasErrors())
			{
				logger.error("ADD PRODUCT - Found #errors: "+ addProductResult.getErrorCount());
				return failure;
			}
			
			Product product = new Product();
			product.setAvailable(addProductForm.isAvailable());
			product.setCost(addProductForm.getCost());
			product.setDescription(addProductForm.getDescription());
			product.setDimensions(addProductForm.getDimensions());
			product.setMaxToBuy(addProductForm.getMaxToBuy());
			product.setMinToBuy(addProductForm.getMinToBuy());
			product.setName(addProductForm.getName());
			product.setQuantity(addProductForm.getQuantity());
			product.setStockQuantity(addProductForm.getStockQuantity());
			product.setTransportCost(addProductForm.getTransportCost());
			product.setUnits(addProductForm.getUnits());

			product.setProducer(userDAO.getUserByUsername(userBean.getUsername()));
			
			if (productDAO.addProduct(product) >=0)
			{
				logger.info("PRODUCT ["+product.getId()+"] ADDED.");
			}
			else
			{
				logger.error("Adding product '"+product.getName()+"' FAILED.");
				failure.addObject("error", "Unable to add product. Check if fields are correct.");
				return failure;
			}
				
		}
		
		return success;
	 }
}
