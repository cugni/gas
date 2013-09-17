package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.Utils;
import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/user/deliverywithdrawals")
@Controller
@RooWebScaffold(path = "user/deliverywithdrawals", formBackingObject = DeliveryWithdrawal.class)

public class UserDeliveryWithdrawalController {

    @RequestMapping(produces = "text/html")
    public String addCollector(@RequestParam(value = "proposal", required = true) Integer proposal,
                               Model uiModel) {

        // rapporto 1 : 1   DW <-> Proposal

        if (DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                Proposal.findProposal(proposal)).getResultList().isEmpty()) // non esiste ancora
        {
            DeliveryWithdrawal dw = new DeliveryWithdrawal();
            dw.setCollector(Utils.getCurrentUser());
            dw.setProposal(Proposal.findProposal(proposal));

            dw.persist();
        }
        else // esiste tuttora
        {
            DeliveryWithdrawal dw = DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                    Proposal.findProposal(proposal)).getSingleResult();

            dw.setCollector(Utils.getCurrentUser());

            dw.merge();
        }

        uiModel.asMap().clear();
        return "redirect:/user/proposals/" + proposal;
    }

    public String list() {
        return "perche' Roo rompe?";
    }
}
