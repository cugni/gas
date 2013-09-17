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

@RequestMapping("/delegate/deliverywithdrawals")
@Controller
@RooWebScaffold(path = "delegate/deliverywithdrawals", formBackingObject = DeliveryWithdrawal.class)
public class DelegateDeliveryWithdrawalController {

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String addDeliveryDate(DeliveryWithdrawal deliveryWithdrawal, Model uiModel,
                         @RequestParam(value = "proposal", required = true) Integer proposal,
                         HttpServletRequest httpServletRequest) {

        // L'unica modifica che puo' fare il delegate e' settare la DELIVERY DATE

        if (DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                Proposal.findProposal(proposal)).getResultList().isEmpty()) // non esiste ancora
        {
            DeliveryWithdrawal dw = new DeliveryWithdrawal();

            dw.setProposal(Proposal.findProposal(proposal));

            dw.setDeliveryDate(Utils.checkRights(deliveryWithdrawal).getDeliveryDate());

            dw.persist();
        }
        else // esiste tuttora
        {
            DeliveryWithdrawal dw = DeliveryWithdrawal.findDeliveryWithdrawalsByProposal(
                    Proposal.findProposal(proposal)).getSingleResult();

            dw.setDeliveryDate(Utils.checkRights(deliveryWithdrawal).getDeliveryDate());

            dw.merge();
        }

        uiModel.asMap().clear();
        return "redirect:/delegate/proposals/" + proposal;
    }




}
