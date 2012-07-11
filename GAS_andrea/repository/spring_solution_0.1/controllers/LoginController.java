package it.polito.ai.gas.controller;

import javax.validation.Valid;

import it.polito.ai.gas.beans.UserBean;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.dao.UserDAO;
import it.polito.ai.gas.forms.LoginForm;
import it.polito.ai.gas.forms.LogoutForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("user")
@Controller
public class LoginController {

	@Autowired
	private Logger logger;
	@Autowired
	private UserDAO userDAO;
    
	@RequestMapping("/login")
	public ModelAndView View(LoginForm loginform) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("login", loginform);
		return mv;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("login") @Valid LoginForm loginform,
			BindingResult result) {
		
		ModelAndView failure = new ModelAndView("login");
		ModelAndView success = new ModelAndView("/logout");
		//ModelAndView success = new ModelAndView("redirect: /"); 

		if (!loginform.getCommand().equals("login"))
		{			
			//System.out.println("Regenerating Login Form... (command = "+ loginform.getCommand() +")");
			failure.addObject("login", new LoginForm()); //resetto la form
			return failure;
		}
		
		if (result.hasErrors())
		{
			logger.error("LOGIN - Found #errors: "+ result.getErrorCount());
			return failure;
		}
		
		User check = userDAO.login(loginform.getUsername(), loginform.getPassword());
			
		if (check == null)
		{
			logger.error("LOGIN FAILED.");
			return failure;
		}
		
		logger.info("USER '"+ check.getUsername() +"' LOGGED IN.");		
		
		UserBean userBean = new UserBean();
		userBean.setUsername(check.getUsername());
		userBean.setRole(check.getRole());
		success.addObject("user", userBean);

		success.addObject("logout", new LogoutForm());
		//success.addObject("register", null);
		return success;
	 }
}
