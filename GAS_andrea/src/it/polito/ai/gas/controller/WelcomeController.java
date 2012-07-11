package it.polito.ai.gas.controller;

import java.util.Date;

import javax.validation.Valid;

import it.polito.ai.gas.beans.IDelegateBean;
import it.polito.ai.gas.beans.IUserBean;
import it.polito.ai.gas.business.Approval;
import it.polito.ai.gas.business.ProducerInfo;
import it.polito.ai.gas.business.Statistics;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.dao.ApprovalDAO;
import it.polito.ai.gas.dao.ProducerInfoDAO;
import it.polito.ai.gas.dao.UserDAO;
import it.polito.ai.gas.forms.LoginForm;
import it.polito.ai.gas.forms.RegisterForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes(value = {"user", "delegate"})
@Controller
public class WelcomeController {

	@Autowired
	private IUserBean userBean;
	@Autowired
	private IDelegateBean delegateBean;
	@Autowired
	private Logger logger;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ApprovalDAO approvalDAO;
	@Autowired
	private ProducerInfoDAO producerInfoDAO;
    
	@RequestMapping("/welcome")
	public ModelAndView View(LoginForm loginform, RegisterForm registerform) {
		ModelAndView mv = new ModelAndView("welcome");
		mv.addObject("login", loginform);
		mv.addObject("register", registerform);
		return mv;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public ModelAndView processForm(
			@ModelAttribute("login") @Valid LoginForm loginform,
			BindingResult loginResult,
			@ModelAttribute("register") @Valid RegisterForm registerform,
			BindingResult registerResult)
	{
		
		ModelAndView failure = new ModelAndView("/welcome");
		ModelAndView success;
		
		
		/* --- LOGIN --- */
		
		if (loginform.getCommand().equals("login"))
		{
			//Resetto la register form, in caso di fallimento...
			failure.addObject("register", new RegisterForm());

			if (loginResult.hasErrors())
			{
				logger.error("LOGIN - Found #errors: "+ loginResult.getErrorCount());
				return failure;
			}
			
			User check = userDAO.login(loginform.getUsername(), loginform.getPassword());
				
			if (check == null)
			{
				logger.error("LOGIN FAILED.");
				failure.addObject("error", "Unable to login. User/password mismatch?");
				return failure;
			}
			
			logger.info("USER '"+ check.getUsername() +"' LOGGED IN.");		
			
			userBean.setUsername(check.getUsername());
			userBean.setRole(check.getRole());
		}
		
		
		/* --- REGISTER --- */
		
		if (registerform.getCommand().equals("register"))
		{
			//Resetto la login form, in caso di fallimento...		
			failure.addObject("login", new LoginForm());
			
			if (registerResult.hasErrors())
			{
				logger.error("REGISTER - Found #errors: "+ loginResult.getErrorCount());
				return failure;
			}
			
			User user = new User();
			
			/* USER */
			user.setUsername(registerform.getUsername());
			user.setPassword(registerform.getPassword());
			user.setRole(registerform.getRole());
			user.setName(registerform.getName());
			user.setSurname(registerform.getSurname());
			user.setBirthDate(new Date(registerform.getYear() -1900,
								registerform.getMonth() -1,
								registerform.getDay()));
			user.setStatistics(new Statistics());
			
			/* PRODUCER INFO */
			if (registerform.getRole() == User.getRoleNumber("producer"))
			{
				User delegate = new User();
				delegate.setUsername(registerform.getDelegate());
				
				ProducerInfo producerInfo = new ProducerInfo();
				producerInfo.setCompanyName(registerform.getCompanyName());
				producerInfo.setDescription(registerform.getDescription());
				producerInfo.setContact(registerform.getContact());
				producerInfo.setAddress(registerform.getAddress());
				producerInfo.setPhoneNumber(registerform.getPhoneNumber());
				producerInfo.setFaxNumber(registerform.getFaxNumber());
				producerInfo.setEmail(registerform.getEmail());
				producerInfo.setPaymentMode(registerform.getPaymentMode());
				producerInfo.setDelegate(delegate);

				/* ADD PRODUCER_INFO */
				if (producerInfoDAO.addProducerInfo(producerInfo) >=0)
					logger.info("PRODUCERINFO ["+producerInfo.getId()+"] ADDED.");
				else
				{
					logger.error("Adding ProducerInfo FAILED.");
					failure.addObject("error", "Unable to add producer info. Invalid fields?");
					return failure;
				}
				
				user.setProducerInfo(producerInfo);
			}
			
			/* ADD USER */
			if (userDAO.addUser(user) != null)
			{
				logger.info("USER '"+user.getUsername()+"' REGISTERED.");
				
				userBean.setUsername(user.getUsername());
				userBean.setRole(user.getRole());
			}
			else
			{
				logger.error("Registering for '"+user.getUsername()+"' FAILED.");
				failure.addObject("error", "Unable to register user. Invalid fields?");
				return failure;
			}
			
			/* ADD APPROVAL */
			Approval approval = new Approval();
			approval.setUser(user);
			if (approvalDAO.addApproval(approval) <0)
			{
				logger.error("Approval adding for '"+ user.getUsername() +"' failed.");
				failure.addObject("error", "Temporary problem. Try again.");
				return failure;
			}
			
			logger.info("APPROVAL ["+approval.getId()+"] ADDED.");

			userBean.setUsername(user.getUsername());
			userBean.setRole(user.getRole());
			
		}
		
		/* Redirect in base al ruolo... */
		switch(userBean.getRole())
		{
			//USER
			case 0:
				success = new ModelAndView("redirect:/pages/home");
				break;
			//DELEGATE
			case 1:
				success = new ModelAndView("redirect:/pages/home_delegate");
				success.addObject("delegate", delegateBean);
				break;
			//PRODUCER
			case 2:
				success = new ModelAndView("redirect:/pages/home_producer");
				break;
				
			default:
				success = new ModelAndView("redirect:/pages/home");
				break;
		}
		success.addObject("user", userBean);

		return success;
	 }
}
