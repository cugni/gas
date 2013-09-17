package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.Utils;
import it.polito.ai.gas.business.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user/proposals")
@Controller
@RooWebScaffold(path = "user/proposals", formBackingObject = Proposal.class, update = false, delete = false, create = false)
public class UserProposalController {
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
        {
            DeliveryWithdrawal dw = Proposal.findProposal(id).getDeliveryWithdrawals().iterator().next();
            uiModel.addAttribute("dw", dw);
            if (dw.getCollector() != null &&
                    dw.getCollector().getId().equals(Utils.getCurrentUser().getId()))
                uiModel.addAttribute("owner", true);
        }

        return "user/proposals/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("proposals", Proposal.findProposalEntries(firstResult, sizeNo));
            float nrOfPages = (float) Proposal.countProposals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("proposals", Proposal.findAllProposals());
        }
        addDateTimeFormatPatterns(uiModel);
        return "user/proposals/list";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("proposal_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("proposal_enddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
}
