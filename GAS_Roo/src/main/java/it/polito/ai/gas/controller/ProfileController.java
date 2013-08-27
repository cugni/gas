package it.polito.ai.gas.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/profile")
@Controller

public class ProfileController {
	@Autowired
	@Qualifier("authenticationManager")
	protected AuthenticationManager authenticationManager;


    @RequestMapping( produces = "text/html")
    public String show(  Model uiModel) {
    	UserDetails userDetails =
    			 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        addDateTimeFormatPatterns(uiModel);

        uiModel.addAttribute("user", userDetails);
        return "profile/show";
    }
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("user_birthdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    @RequestMapping( params = "form", produces = "text/html")
    public String updateForm(Model uiModel) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //devo avere una copia con solo i parametri che mi servono
        User modelUser = new User();
        modelUser.setUsername(currentUser.getUsername());
        modelUser.setBirthDate(currentUser.getBirthDate());
        modelUser.setName(currentUser.getName());
        modelUser.setSurname(currentUser.getSurname());


        populateEditForm(uiModel, modelUser);
        return "profile/update";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@ModelAttribute("modelUser") User modelUser, Model uiModel,  BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, modelUser);
            return "profile/update";
        }

        User newUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // cambio i campi che si potevano modificare...
        newUser.setUsername(modelUser.getUsername());
        newUser.setName(modelUser.getName());
        newUser.setSurname(modelUser.getSurname());
        newUser.setBirthDate(modelUser.getBirthDate());

        uiModel.asMap().clear();
        newUser.merge().persist();
        return "redirect:/profile";
    }

    void populateEditForm(Model uiModel, User user) {
        /*
        uiModel.addAttribute("username", user.getUsername());
        uiModel.addAttribute("name", ((User) user).getName());
        uiModel.addAttribute("surname", ((User) user).getSurname());
        */
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("modelUser", user);
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
