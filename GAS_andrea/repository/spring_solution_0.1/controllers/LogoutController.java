package it.polito.ai.gas.controller;

import it.polito.ai.gas.beans.UserBean;
import it.polito.ai.gas.forms.LoginForm;
import it.polito.ai.gas.forms.LogoutForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("user")
@Controller
public class LogoutController {

	@Autowired
	private Logger logger;
    
	@RequestMapping("/logout")
	public ModelAndView View(LogoutForm logoutform) {
		ModelAndView mv = new ModelAndView("logout");
		mv.addObject("logout", logoutform);
		return mv;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("logout") LogoutForm logoutform) {
		
		//ModelAndView success = new ModelAndView("/login");
		ModelAndView success = new ModelAndView("redirect: /"); 
		
		if (!logoutform.getCommand().equals("logout"))
		{
			//System.out.println("Regenerating Logout Form... (command = "+ logoutform.getCommand() +")");
			//mv.addObject("logout", new LogoutForm()); //resetto la form
			return new ModelAndView("logout");
		}
		
		success.addObject("user", new UserBean());
		
		success.addObject("login", new LoginForm());

		logger.info("USER LOGGED OUT.");
		
		return success;
	 }
}
