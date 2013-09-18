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

    @RequestMapping(value = "/addCollector", produces = "text/html")
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

    @RequestMapping(value = "/removeCollector", produces = "text/html")
    public String removeCollector(@RequestParam(value = "proposal", required = true) Integer proposal,
                               Model uiModel) {

        // rapporto 1 : 1   DW <-> Proposal

        if (DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                Proposal.findProposal(proposal)).getResultList().isEmpty()) // non esiste ancora
        {
            return "redirect:/user/proposals/" + proposal;
        }
        else // esiste tuttora
        {
            DeliveryWithdrawal dw = DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                    Proposal.findProposal(proposal)).getSingleResult();

            if (dw.getCollector().getId().equals(Utils.getCurrentUser().getId()))
            {
                dw.setCollector(null);
                dw.setWithdrawalDate(null);
            }

            dw.merge();
        }

        uiModel.asMap().clear();
        return "redirect:/user/proposals/" + proposal;
    }

    @RequestMapping(value = "/update", produces = "text/html")
    public String update(DeliveryWithdrawal deliveryWithdrawal, Model uiModel,
                                  @RequestParam(value = "proposal", required = true) Integer proposal,
                                  HttpServletRequest httpServletRequest) {

        // L'unica modifica che puo' fare il delegate e' settare la DELIVERY DATE

        if (DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                Proposal.findProposal(proposal)).getResultList().isEmpty()) // non esiste ancora
        {
            DeliveryWithdrawal dw = new DeliveryWithdrawal();

            dw.setWithdrawalDate(deliveryWithdrawal.getWithdrawalDate());
            dw.setAddress(deliveryWithdrawal.getAddress());

            dw.setProposal(Proposal.findProposal(proposal));

            dw.persist();
        }
        else // esiste tuttora
        {
            DeliveryWithdrawal dw = DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                    Proposal.findProposal(proposal)).getSingleResult();

            dw.setAddress(deliveryWithdrawal.getAddress());
            dw.merge();

            if (dw.getDeliveryDate() == null)
            {
                uiModel.asMap().clear();
                uiModel.addAttribute("error",
                        "Can't set withdrawal date if delivery date has not been set by delegate!");

                uiModel.addAttribute("deliverywithdrawal", dw);

                return "user/deliverywithdrawals/show";
            } else if (dw.getDeliveryDate().after(deliveryWithdrawal.getWithdrawalDate()))
            {
                uiModel.asMap().clear();
                uiModel.addAttribute("error",
                        "Can't set withdrawal date before delivery date!");

                uiModel.addAttribute("deliverywithdrawal", dw);

                return "user/deliverywithdrawals/show";
            } else {
                // ok
                dw.setWithdrawalDate(deliveryWithdrawal.getWithdrawalDate());

                uiModel.asMap().clear();
                dw.merge();
            }
        }

        return "redirect:/user/proposals/" + proposal;
    }


    public String list() {
        return "perche' Roo rompe?";
    }
}
