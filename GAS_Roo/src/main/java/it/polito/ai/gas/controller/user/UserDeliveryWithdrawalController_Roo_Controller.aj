// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.controller.user.UserDeliveryWithdrawalController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect UserDeliveryWithdrawalController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UserDeliveryWithdrawalController.create(@Valid DeliveryWithdrawal deliveryWithdrawal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, deliveryWithdrawal);
            return "user/deliverywithdrawals/create";
        }
        uiModel.asMap().clear();
        deliveryWithdrawal.persist();
        return "redirect:/user/deliverywithdrawals/" + encodeUrlPathSegment(deliveryWithdrawal.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UserDeliveryWithdrawalController.createForm(Model uiModel) {
        populateEditForm(uiModel, new DeliveryWithdrawal());
        return "user/deliverywithdrawals/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String UserDeliveryWithdrawalController.show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("deliverywithdrawal", DeliveryWithdrawal.findDeliveryWithdrawal(id));
        uiModel.addAttribute("itemId", id);
        return "user/deliverywithdrawals/show";
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String UserDeliveryWithdrawalController.updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, DeliveryWithdrawal.findDeliveryWithdrawal(id));
        return "user/deliverywithdrawals/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String UserDeliveryWithdrawalController.delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        DeliveryWithdrawal deliveryWithdrawal = DeliveryWithdrawal.findDeliveryWithdrawal(id);
        deliveryWithdrawal.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/user/deliverywithdrawals";
    }
    
    void UserDeliveryWithdrawalController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("deliveryWithdrawal_deliverydate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("deliveryWithdrawal_withdrawaldate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void UserDeliveryWithdrawalController.populateEditForm(Model uiModel, DeliveryWithdrawal deliveryWithdrawal) {
        uiModel.addAttribute("deliveryWithdrawal", deliveryWithdrawal);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("proposals", Proposal.findAllProposals());
        uiModel.addAttribute("users", User.findAllUsers());
    }
    
    String UserDeliveryWithdrawalController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
