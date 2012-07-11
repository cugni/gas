package it.polito.ai.gas.controller;

import java.util.Date;

import javax.validation.Valid;

import it.polito.ai.gas.beans.IUser;
import it.polito.ai.gas.beans.UserBean;
import it.polito.ai.gas.business.Approval;
import it.polito.ai.gas.business.ProducerInfo;
import it.polito.ai.gas.business.Statistics;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.dao.ApprovalDAO;
import it.polito.ai.gas.dao.ProducerInfoDAO;
import it.polito.ai.gas.dao.UserDAO;
import it.polito.ai.gas.forms.RegisterForm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("user")
@Controller
public class RegisterController {

	@Autowired
	private IUser userBean;
	@Autowired
	private Logger logger;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ApprovalDAO approvalDAO;
	@Autowired
	private ProducerInfoDAO producerInfoDAO;
    
	@RequestMapping("/register")
	public ModelAndView View(RegisterForm registerform) {
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("register", registerform);
		return mv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView handler(@ModelAttribute("register") @Valid RegisterForm registerform,
			BindingResult result) {
		
		ModelAndView mv = new ModelAndView("register");;
		
		if (!registerform.getCommand().equals("register"))
		{
			mv.addObject("register", new RegisterForm());
			return mv;
		}
		
		if (result.hasErrors())
		{
			logger.error("REGISTER. Found #errors: "+ result.getErrorCount());
			return mv;
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
				logger.error("Adding ProducerInfo FAILED.");
			
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
			return mv;
		}
		
		/* ADD APPROVAL */
		Approval approval = new Approval();
		approval.setUser(user);
		if (approvalDAO.addApproval(approval) <0)
		{
			logger.error("Approval adding for '"+ user.getUsername() +"' failed.");
			return mv;
		}
		
		logger.info("APPROVAL ["+approval.getId()+"] ADDED.");

		UserBean userBean = new UserBean();
		userBean.setUsername(user.getUsername());
		userBean.setRole(user.getRole());
		
		mv.addObject("user", userBean);
		return mv;
	 }

}
