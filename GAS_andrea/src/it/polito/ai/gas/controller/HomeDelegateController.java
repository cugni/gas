package it.polito.ai.gas.controller;

import it.polito.ai.gas.beans.UserBean;
import it.polito.ai.gas.forms.LoginForm;
import it.polito.ai.gas.forms.LogoutForm;
import it.polito.ai.gas.forms.RegisterForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("user")
@Controller
public class HomeDelegateController {

	@Autowired
	private Logger logger;
	
	@RequestMapping("/home_delegate")
	public ModelAndView View(LogoutForm logoutForm) {
		ModelAndView mv = new ModelAndView("home_delegate");
		mv.addObject("logout", logoutForm);
		return mv;
	}
	
	@RequestMapping(value = "/home_delegate", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("logout") LogoutForm logoutForm)
	{
		
		ModelAndView success = null; //dipende dall'azione
		//ModelAndView failure = new ModelAndView("/home");
		
		/* --- LOGOUT --- */
		if (logoutForm.getCommand().equals("logout"))
		{	
			success = new ModelAndView("redirect:/pages/welcome"); 

			success.addObject("user", new UserBean());
			
			success.addObject("login", new LoginForm());
			success.addObject("register", new RegisterForm());
	
			logger.info("USER LOGGED OUT.");	
		}
		
		return success;
	 }
}
