// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.PurchaseRequestPart;
import it.polito.ai.gas.business.User;
import it.polito.ai.gas.controller.user.UserPurchaseRequestPartController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect UserPurchaseRequestPartController_Roo_Controller {
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UserPurchaseRequestPartController.createForm(Model uiModel) {
        populateEditForm(uiModel, new PurchaseRequestPart());
        return "user/purchaserequestparts/create";
    }
    
    void UserPurchaseRequestPartController.populateEditForm(Model uiModel, PurchaseRequestPart purchaseRequestPart) {
        uiModel.addAttribute("purchaseRequestPart", purchaseRequestPart);
        uiModel.addAttribute("purchaserequests", PurchaseRequest.findAllPurchaseRequests());
        uiModel.addAttribute("users", User.findAllUsers());
    }
    
    String UserPurchaseRequestPartController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
