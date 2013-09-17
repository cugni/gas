package it.polito.ai.gas.controller.delegate;

import it.polito.ai.gas.Utils;
import it.polito.ai.gas.business.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import java.util.List;

@RequestMapping("/delegate/proposals")
@Controller
@RooWebScaffold(path = "delegate/proposals", formBackingObject = Proposal.class)
public class DelegateProposalController {

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {

        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("proposal", Proposal.findProposal(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("incomplete", PurchaseRequest.findIncompletePurchaseRequests(
                Proposal.findProposal(id)).getResultList());

        uiModel.addAttribute("message", new Message());

        // se ancora non abbiamo creato una DW o c'e' ma non ha ancora un collector assegnato
        if (Proposal.findProposal(id).getDeliveryWithdrawals().isEmpty() ||
                !Proposal.findProposal(id).getDeliveryWithdrawals().iterator().hasNext())
            uiModel.addAttribute("dw", new DeliveryWithdrawal());
        else
            uiModel.addAttribute("dw", Proposal.findProposal(id).getDeliveryWithdrawals().iterator().next());


        return "delegate/proposals/show";
    }
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("proposal_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("proposal_enddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("proposals", Proposal.findProposalEntriesByDelegate(user, firstResult, sizeNo));
            float nrOfPages = (float) Proposal.countProposals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {

            uiModel.addAttribute("proposals", Proposal.findProposalsByDelegate(user).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegate/proposals/list";
    }

    private List<Product> findMyProducts() {
        User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<Product> products = new ArrayList<Product>();

        for(Producer producer : Producer.findProducersByDelegate(user).getResultList())
            products.addAll(producer.getProducts());

        return products;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Proposal proposal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        Utils.checkRights(proposal);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, proposal);
            return "delegate/proposals/update";
        }

        if (proposal.getStartDate().after(proposal.getEndDate()))
        {
            uiModel.addAttribute("error", "A proposal can not start after its end");

            populateEditForm(uiModel, proposal);
            return "delegate/proposals/create";
        }

        proposal.setDelegate(Utils.getCurrentUser());

        uiModel.asMap().clear();
        proposal.merge();
        return "redirect:/delegate/proposals/" + encodeUrlPathSegment(proposal.getId().toString(), httpServletRequest);
    }
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create( Proposal proposal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       // checkRights(proposal);   !No, se la stai creando, ovvio non è lui l'il proposers <- parlar en català?
        proposal.setDelegate(Utils.getCurrentUser());
        if (bindingResult.hasErrors()) {

            populateEditForm(uiModel, proposal);
            return "delegate/proposals/create";
        }

        /* CHECK */
        if (proposal.getStartDate().after(proposal.getEndDate()))
        {
            uiModel.addAttribute("error", "A proposal can not start after its end");

            populateEditForm(uiModel, proposal);
            return "delegate/proposals/create";
        }

        uiModel.asMap().clear();
        proposal.persist();

        return "redirect:/delegate/proposals/" + encodeUrlPathSegment(proposal.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel,Utils.checkRights(Proposal.findProposal(id)));
        ArrayList<User> mockUser = new ArrayList<User>();
        mockUser.add(Utils.getCurrentUser());
        uiModel.addAttribute("users", mockUser);
        return "delegate/proposals/update";
    }
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Proposal());
        return "delegate/proposals/create";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Proposal proposal = Utils.checkRights(Proposal.findProposal(id));
        proposal.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/delegate/proposal";
    }


    void populateEditForm(Model uiModel, Proposal proposal) {


        uiModel.addAttribute("proposal", proposal);

        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("messages", Message.findAllMessages());
        uiModel.addAttribute("products", findMyProducts());

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
