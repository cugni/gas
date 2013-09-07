package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.User;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@RequestMapping("/user/purchaserequest")
@Controller
@RooWebScaffold(path = "user/purchaserequest", formBackingObject = PurchaseRequest.class)
public class UserPurchaseRequestController {

    @RequestMapping(value = "/purchase/{id}", produces = "text/html")
    public String purchase(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.asMap().clear();
        /*
        ArrayList<Proposal> mock = new ArrayList<Proposal>();
        mock.add(Proposal.findProposal(id));
        uiModel.addAttribute("proposals", mock );
         */
        uiModel.addAttribute("proposal", Proposal.findProposal(id) );
        uiModel.addAttribute("proposal_id", id );

        uiModel.addAttribute("purchaseRequest", new PurchaseRequest());
        return "user/purchaserequest/purchase";

    }

    public PurchaseRequest checkRights(PurchaseRequest purchaseRequest){
        User  user  =    getCurrentUser();

        if(!user.equals(purchaseRequest.getAcquirer()))
            throw new SecurityException("A user can modify only his own purchase requests");
        return purchaseRequest;
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("purchaserequests",
                    PurchaseRequest.findPurchaseRequestsByAcquirer(user)
                            .setFirstResult(firstResult)
                            .setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) PurchaseRequest.countPurchaseRequests() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("purchaserequests", PurchaseRequest.findPurchaseRequestsByAcquirer(getCurrentUser()));
        }
        return "user/purchaserequest/list";
    }
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PurchaseRequest purchaseRequest,
                         BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
if(!user.equals(purchaseRequest.getAcquirer()))
    throw new SecurityException("An user can modify only his own purchase requests");
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequest);
            return "user/purchaserequest/update";
        }
        uiModel.asMap().clear();
        purchaseRequest.merge();
        return "redirect:/user/purchaserequest/" + encodeUrlPathSegment(purchaseRequest.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(PurchaseRequest purchaseRequest, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest,
                         @RequestParam(required = false)Integer proposal) {
      //  checkRights(purchaseRequest);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequest);
            return "user/purchaserequest/create";
        }

        purchaseRequest.setAcquirer(getCurrentUser());
        /* TO-DO: qua mettiamo il check per vedere se è completa o se dobbiamo fare delle parti... */
        purchaseRequest.setCompleted(true);

        purchaseRequest.setReceived(false);

        uiModel.asMap().clear();

        purchaseRequest.persist();
        return "redirect:/user/purchaserequest/" + encodeUrlPathSegment(purchaseRequest.getId().toString(), httpServletRequest);
    }
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("purchaserequest", checkRights(PurchaseRequest.findPurchaseRequest(id)));
        uiModel.addAttribute("itemId", id);
        return "user/purchaserequest/show";
    }
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, checkRights(PurchaseRequest.findPurchaseRequest(id)));
        return "user/purchaserequest/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PurchaseRequest purchaseRequest = checkRights(PurchaseRequest.findPurchaseRequest(id));
        purchaseRequest.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/user/purchaserequest";
    }

}
