package it.polito.ai.gas.controller.user;
import it.polito.ai.gas.Utils;
import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.PurchaseRequestPart;
import it.polito.ai.gas.business.User;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/purchaserequestparts")
@Controller
@RooWebScaffold(path = "user/purchaserequestparts", formBackingObject = PurchaseRequestPart.class)
public class UserPurchaseRequestPartController {

    public PurchaseRequestPart checkRights(PurchaseRequestPart purchaseRequestPart){
        User user  = Utils.getCurrentUser();
        if(!user.equals(purchaseRequestPart.getAcquirer()))
            throw new SecurityException("An user can modify only his own purchase requests");
        return purchaseRequestPart;
    }
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PurchaseRequestPart purchaseRequestPart, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequestPart);
            return "user/purchaserequestparts/create";
        }
        uiModel.asMap().clear();
        purchaseRequestPart.setAcquirer(Utils.getCurrentUser());
        purchaseRequestPart.persist();
        return "redirect:/user/purchaserequestparts/" + encodeUrlPathSegment(purchaseRequestPart.getId().toString(), httpServletRequest);
    }



    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id,
                       Model uiModel) {
        uiModel.addAttribute("purchaserequestpart", checkRights(PurchaseRequestPart.findPurchaseRequestPart(id)));
        uiModel.addAttribute("itemId", id);
        return "user/purchaserequestparts/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;

            uiModel.addAttribute("purchaserequestparts",
                    PurchaseRequestPart.findPurchaseRequestPartsByAcquirer(Utils.getCurrentUser()).setFirstResult(firstResult)
                    .setMaxResults(sizeNo));
            float nrOfPages = (float) PurchaseRequestPart.countPurchaseRequestParts() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("purchaserequestparts", PurchaseRequestPart.findPurchaseRequestPartsByAcquirer(Utils.getCurrentUser()).getResultList());
        }
        return "user/purchaserequestparts/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PurchaseRequestPart purchaseRequestPart, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequestPart);
            return "user/purchaserequestparts/update";
        }
        uiModel.asMap().clear();
        purchaseRequestPart.merge();
        return "redirect:/user/purchaserequestparts/" + encodeUrlPathSegment(purchaseRequestPart.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, PurchaseRequestPart.findPurchaseRequestPart(id));
        return "user/purchaserequestparts/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PurchaseRequestPart purchaseRequestPart = PurchaseRequestPart.findPurchaseRequestPart(id);
        purchaseRequestPart.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/user/purchaserequestparts";
    }
}
