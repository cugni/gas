package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/proposals")
@Controller
@RooWebScaffold(path = "proposals", formBackingObject = Proposal.class)
@RooWebJson(jsonObject = Proposal.class)
public class ProposalController {

    @RequestMapping(params = "product", produces = "text/html")
    public String createFormProduct(@RequestParam("product") Integer idp, Model uiModel) {
        Proposal p = new Proposal();
        populateEditForm(uiModel, p);
        p.setProduct(Product.findProduct(idp));
        uiModel.addAttribute("product", p.getProduct());
        return "proposals/createfromproduct";
    }

    @RequestMapping(params = "product", method = RequestMethod.POST, produces = "text/html")
    public String createWithProduct(@Valid Proposal proposal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            Proposal p = new Proposal();
            populateEditForm(uiModel, p);
            uiModel.addAttribute("product", p.getProduct());
            return "proposals/createfromproduct";
        }
        uiModel.asMap().clear();
        proposal.persist();
        return "redirect:/proposal/" + encodeUrlPathSegment(proposal.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}/chat", produces = "text/html")
    public String chatRead(@PathVariable("id") Integer id, Model uiModel) {
        Proposal p = Proposal.findProposal(id);
        uiModel.addAttribute("messages", Message.findMessagesByOrder(p));
        return "proposals/chat";
    }
}
