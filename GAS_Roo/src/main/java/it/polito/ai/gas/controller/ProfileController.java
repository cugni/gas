package it.polito.ai.gas.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Producer;
import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.business.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/profile/**")
@Controller
public class ProfileController {
	@Autowired
	@Qualifier("authenticationManager")
	protected AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "profile/index";
    }
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new User());
        return "users/create";
    }
    
    @RequestMapping(value = "/", produces = "text/html")
    public String show(  Model uiModel) {
    	UserDetails userDetails =
    			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("user", userDetails);
      
        return "users/show";
    }
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("user_birthdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "users/update";
        }
        uiModel.asMap().clear();
        user.merge();
        return "redirect:/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, User.findUser(id));
        return "users/update";
    }
    void populateEditForm(Model uiModel, User user) {
        uiModel.addAttribute("user", user);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("deliverywithdrawals", DeliveryWithdrawal.findAllDeliveryWithdrawals());
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("messages", Message.findAllMessages());
        uiModel.addAttribute("producers", Producer.findAllProducers());
        uiModel.addAttribute("purchaserequests", PurchaseRequest.findAllPurchaseRequests());
        uiModel.addAttribute("usertypes", Arrays.asList(UserType.values()));
    }
    
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
