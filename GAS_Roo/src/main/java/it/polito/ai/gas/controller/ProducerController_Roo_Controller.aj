// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Producer;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.PurchaseRequestPart;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.business.UserType;
import it.polito.ai.gas.controller.ProducerController;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

privileged aspect ProducerController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ProducerController.create(@Valid Producer producer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, producer);
            return "admin/producers/create";
        }
        uiModel.asMap().clear();
        producer.persist();
        return "redirect:/admin/producers/" + encodeUrlPathSegment(producer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ProducerController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Producer());
        return "admin/producers/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ProducerController.show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("producer", Producer.findProducer(id));
        uiModel.addAttribute("itemId", id);
        return "admin/producers/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ProducerController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("producers", Producer.findProducerEntries(firstResult, sizeNo));
            float nrOfPages = (float) Producer.countProducers() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("producers", Producer.findAllProducers());
        }
        addDateTimeFormatPatterns(uiModel);
        return "admin/producers/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ProducerController.update(@Valid Producer producer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, producer);
            return "admin/producers/update";
        }
        uiModel.asMap().clear();
        producer.merge();
        return "redirect:/admin/producers/" + encodeUrlPathSegment(producer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ProducerController.updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, Producer.findProducer(id));
        return "admin/producers/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ProducerController.delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Producer producer = Producer.findProducer(id);
        producer.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/admin/producers";
    }
    
    void ProducerController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("producer_birthdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void ProducerController.populateEditForm(Model uiModel, Producer producer) {
        uiModel.addAttribute("producer", producer);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("deliverywithdrawals", DeliveryWithdrawal.findAllDeliveryWithdrawals());
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("messages", Message.findAllMessages());
        uiModel.addAttribute("producers", Producer.findAllProducers());
        uiModel.addAttribute("products", Product.findAllProducts());
        uiModel.addAttribute("proposals", Proposal.findAllProposals());
        uiModel.addAttribute("purchaserequests", PurchaseRequest.findAllPurchaseRequests());
        uiModel.addAttribute("purchaserequestparts", PurchaseRequestPart.findAllPurchaseRequestParts());
        uiModel.addAttribute("users", User.findAllUsers());
        uiModel.addAttribute("usertypes", Arrays.asList(UserType.values()));
    }
    
    String ProducerController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
