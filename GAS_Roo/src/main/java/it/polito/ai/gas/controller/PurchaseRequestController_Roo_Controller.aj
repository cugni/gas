// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.PurchaseRequestPart;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.controller.PurchaseRequestController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect PurchaseRequestController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PurchaseRequestController.create(@Valid PurchaseRequest purchaseRequest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequest);
            return "admin/purchaserequests/create";
        }
        uiModel.asMap().clear();
        purchaseRequest.persist();
        return "redirect:/admin/purchaserequests/" + encodeUrlPathSegment(purchaseRequest.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PurchaseRequestController.createForm(Model uiModel) {
        populateEditForm(uiModel, new PurchaseRequest());
        return "admin/purchaserequests/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String PurchaseRequestController.show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("purchaserequest", PurchaseRequest.findPurchaseRequest(id));
        uiModel.addAttribute("itemId", id);
        return "admin/purchaserequests/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PurchaseRequestController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("purchaserequests", PurchaseRequest.findPurchaseRequestEntries(firstResult, sizeNo));
            float nrOfPages = (float) PurchaseRequest.countPurchaseRequests() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("purchaserequests", PurchaseRequest.findAllPurchaseRequests());
        }
        return "admin/purchaserequests/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PurchaseRequestController.update(@Valid PurchaseRequest purchaseRequest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequest);
            return "admin/purchaserequests/update";
        }
        uiModel.asMap().clear();
        purchaseRequest.merge();
        return "redirect:/admin/purchaserequests/" + encodeUrlPathSegment(purchaseRequest.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String PurchaseRequestController.updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, PurchaseRequest.findPurchaseRequest(id));
        return "admin/purchaserequests/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String PurchaseRequestController.delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PurchaseRequest purchaseRequest = PurchaseRequest.findPurchaseRequest(id);
        purchaseRequest.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/admin/purchaserequests";
    }
    
    void PurchaseRequestController.populateEditForm(Model uiModel, PurchaseRequest purchaseRequest) {
        uiModel.addAttribute("purchaseRequest", purchaseRequest);
        uiModel.addAttribute("proposals", Proposal.findAllProposals());
        uiModel.addAttribute("purchaserequestparts", PurchaseRequestPart.findAllPurchaseRequestParts());
        uiModel.addAttribute("users", User.findAllUsers());
    }
    
    String PurchaseRequestController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
