package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.Utils;
import it.polito.ai.gas.business.*;
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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@RequestMapping("/user/purchaserequest")
@Controller
@RooWebScaffold(path = "user/purchaserequest", formBackingObject = PurchaseRequest.class)
public class UserPurchaseRequestController {

    @RequestMapping(value = "/purchase/{id}", produces = "text/html")
    public String purchase(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.asMap().clear();

        uiModel.addAttribute("proposal", Proposal.findProposal(id) );
        uiModel.addAttribute("proposal_id", id );

        uiModel.addAttribute("purchaseRequest", new PurchaseRequest());
        return "user/purchaserequest/purchase";

    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       User  user  = Utils.getCurrentUser();

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
            uiModel.addAttribute("purchaserequests", PurchaseRequest.findPurchaseRequestsByAcquirer(Utils.getCurrentUser()));
        }
        return "user/purchaserequest/list";
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {

        PurchaseRequest pr =  Utils.checkRights(PurchaseRequest.findPurchaseRequest(id));
        uiModel.addAttribute("purchaseRequest", pr);

        ArrayList<Proposal> mockProposal = new ArrayList<Proposal>();
        mockProposal.add(pr.getProposal());
        uiModel.addAttribute("proposals", mockProposal);

        uiModel.addAttribute("purchaserequestparts", pr.getPurchaseRequestParts());

        ArrayList<User> mockUser = new ArrayList<User>();
        mockUser.add(pr.getAcquirer());
        uiModel.addAttribute("users", mockUser);

        uiModel.addAttribute("completed", pr.getCompleted());

        return "user/purchaserequest/update";
    }
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PurchaseRequest purchaseRequest,
                         BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {

        Utils.checkRights(purchaseRequest);

        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequest);
            return "user/purchaserequest/update";
        }


        if (purchaseRequest.getToMin() == 0)    // e' completa. Molt Ben!
        {
            purchaseRequest.setCompleted(true);
            purchaseRequest.merge();

        }
        else // non e' completa
        {
            purchaseRequest.setCompleted(false);
            purchaseRequest.merge();

            PurchaseRequestPart part = new PurchaseRequestPart();
            part.setPurchaseRequest(purchaseRequest);
            part.setAcquirer(purchaseRequest.getAcquirer());
            part.setQuantity(purchaseRequest.getQuantity());

            part.persist();


        }

        checkProposalMinReached(purchaseRequest.getProposal());

        uiModel.asMap().clear();
        purchaseRequest.merge();
        return "redirect:/user/purchaserequest/" + encodeUrlPathSegment(purchaseRequest.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(PurchaseRequest purchaseRequest, BindingResult bindingResult, Model uiModel,
                         HttpServletRequest httpServletRequest,
                         @RequestParam(required = false)Integer proposal,
                         @RequestParam(required = false)Integer addTo) {
      //  checkRights(purchaseRequest);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, purchaseRequest);
            return "user/purchaserequest/create";
        }

        int pag_id = 0;

        purchaseRequest.setAcquirer(Utils.getCurrentUser());
        purchaseRequest.setReceived(false);

        /* TO-DO: qua mettiamo il check per vedere se è completa o se dobbiamo fare delle parti... */

        if (addTo != null) // se la sto aggiungendo ad una gia' esistente!
        {
            PurchaseRequest pr = PurchaseRequest.findPurchaseRequest(addTo); //aggiungo a questa
            pr.setQuantity(pr.getQuantity() + purchaseRequest.getQuantity());

            if ( pr.getToMin() == 0)
            {
                pr.setCompleted(true);

                checkProposalMinReached(pr.getProposal());
            }

            pr.merge();

            //ignoro la mia "purchaserequest" e memorizzo la parte

            PurchaseRequestPart part = new PurchaseRequestPart();
            part.setPurchaseRequest(PurchaseRequest.findPurchaseRequest(addTo));
            part.setAcquirer(purchaseRequest.getAcquirer());
            part.setQuantity(purchaseRequest.getQuantity());

            part.persist();

            pag_id = pr.getProposal().getId();
        }
         else
        {
            // se non sto aggiungendo a niente
            if (purchaseRequest.getToMin() == 0)    // e' completa. Molt Ben!
            {
                purchaseRequest.setCompleted(true);
                purchaseRequest.persist();

            }
            else // non e' completa
            {
                purchaseRequest.setCompleted(false);
                purchaseRequest.persist();

                PurchaseRequestPart part = new PurchaseRequestPart();
                part.setPurchaseRequest(purchaseRequest);
                part.setAcquirer(purchaseRequest.getAcquirer());
                part.setQuantity(purchaseRequest.getQuantity());

                part.persist();


            }

            checkProposalMinReached(purchaseRequest.getProposal());
            pag_id = purchaseRequest.getProposal().getId();
        }
        uiModel.asMap().clear();

        return "redirect:/user/proposals/" + pag_id;
    }

    private void checkProposalMinReached(Proposal proposal) {

        int tot = 0;
        for(PurchaseRequest pr : proposal.getPurchaseRequests())
        {
             tot += pr.getQuantity();
        }

        if (tot >= proposal.getProduct().getMinToBuyOrder())
            proposal.setMinReached(true);
        else
            proposal.setMinReached(false);

        proposal.merge();

    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id,
                       @RequestParam(value = "incomplete", required = false) String incomplete,
                       Model uiModel) {
        uiModel.addAttribute("purchaserequest", Utils.checkRights(PurchaseRequest.findPurchaseRequest(id)));
        uiModel.addAttribute("itemId", id);

        if (incomplete != null)
        {
            uiModel.addAttribute("incomplete", true);
            uiModel.addAttribute("toMin", PurchaseRequest.findPurchaseRequest(id).getToMin());

            uiModel.addAttribute("addTo", id);

            uiModel.addAttribute("newpurchaserequest", new PurchaseRequest());
            ArrayList<Proposal> mock = new ArrayList<Proposal>();
            mock.add(PurchaseRequest.findPurchaseRequest(id).getProposal());
            uiModel.addAttribute("proposals", mock);

        }

        return "user/purchaserequest/show";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PurchaseRequest purchaseRequest = Utils.checkRights(PurchaseRequest.findPurchaseRequest(id));
        purchaseRequest.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());

        checkProposalMinReached(purchaseRequest.getProposal());

        return "redirect:/user/purchaserequest";
    }



    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PurchaseRequest());
        return "user/purchaserequest/create";
    }

    void populateEditForm(Model uiModel, PurchaseRequest purchaseRequest) {
        uiModel.addAttribute("purchaseRequest", purchaseRequest);

        uiModel.addAttribute("proposals", Proposal.findAllProposals());

        uiModel.addAttribute("purchaserequestparts", PurchaseRequestPart.findAllPurchaseRequestParts());
        uiModel.addAttribute("users", User.findAllUsers());
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
