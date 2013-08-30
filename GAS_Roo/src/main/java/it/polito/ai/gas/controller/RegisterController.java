package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@RequestMapping("/register")
@Controller
public class RegisterController {

    @RequestMapping("success")
    public String success(Model uiModel) {
        User currentUser =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        uiModel.addAttribute("user", currentUser);
        uiModel.addAttribute("itemId", currentUser.getId());
        return "/success";
    }

    @RequestMapping(params = "form",produces = "text/html")
    public String createRegisterForm(Model uiModel) {
        populateEditForm(uiModel, new User());
        return "/register/form";
    }

    @RequestMapping("producer")
    public String createRegisterProducerForm(Model uiModel) {

        //populateEditForm(uiModel, new User());
        populateEditForm_Producer(uiModel, new Producer());

        return "/registerProducer";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String register(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        user.setApproved(false);
        user.setRole(UserType.ROLE_USER);    //TO-DO ...e il delegate?
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "/register/form";
        }
        user.persist();
        return "redirect:/success";
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

    void populateEditForm_Producer(Model uiModel, Producer producer) {
        uiModel.addAttribute("producer", producer);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("deliverywithdrawals", DeliveryWithdrawal.findAllDeliveryWithdrawals());
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("messages", Message.findAllMessages());
        uiModel.addAttribute("producers", Producer.findAllProducers());
        uiModel.addAttribute("products", Product.findAllProducts());
        uiModel.addAttribute("purchaserequests", PurchaseRequest.findAllPurchaseRequests());
        uiModel.addAttribute("users", User.findAllUsers());
        uiModel.addAttribute("usertypes", Arrays.asList(UserType.values()));
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("user_birthdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
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
