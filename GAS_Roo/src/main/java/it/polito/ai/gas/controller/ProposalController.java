package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import java.util.Calendar;
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

@RequestMapping("/admin/proposals")
@Controller
@RooWebScaffold(path = "admin/proposals", formBackingObject = Proposal.class)
@RooWebJson(jsonObject = Proposal.class)
public class ProposalController {

    @RequestMapping(params = "product", produces = "text/html")
    public String createFormProduct(@RequestParam("product") Integer idp, Model uiModel) {
        Proposal p = new Proposal();
        populateEditForm(uiModel, p);
        p.setProduct(Product.findProduct(idp));
        uiModel.addAttribute("product", p.getProduct());
        return "admin/proposals/createfromproduct";
    }

    @RequestMapping(params = "product", method = RequestMethod.POST, produces = "text/html")
    public String createWithProduct(@Valid Proposal proposal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            Proposal p = new Proposal();
            populateEditForm(uiModel, p);
            uiModel.addAttribute("product", p.getProduct());
            return "admin/proposals/createfromproduct";
        }
        uiModel.asMap().clear();
        proposal.persist();
        return "redirect:/proposal/" + encodeUrlPathSegment(proposal.getId().toString(), httpServletRequest);
    }

    private String createChatForm(Integer id, Model uiModel) {
        Proposal p = Proposal.findProposal(id);
        uiModel.addAttribute("old_messages", Message.findMessagesByOrder(p));
        Message msg = new Message();
        msg.setOrder(p);
        msg.setUser(User.findAllUsers().get(0));
        msg.setDate(Calendar.getInstance());
        uiModel.addAttribute("message", msg);
        return "admin/proposals/chat";
    }

    @RequestMapping(value = "/{id}", params = "chat", produces = "text/html")
    public String chatRead(@PathVariable("id") Integer id, Model uiModel) {
        return createChatForm(id, uiModel);
    }

    @RequestMapping(value = "/{id}", params = "chat", produces = "text/html", method = RequestMethod.POST)
    public String chatWrite(@Valid Message message, @PathVariable("id") Integer id, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        Proposal p = Proposal.findProposal(id);
        if (bindingResult.hasErrors()) {
            return createChatForm(id, uiModel);
        }
        uiModel.asMap().clear();
        message.persist();
        return "redirect:/proposals/" + encodeUrlPathSegment(p.getId().toString(), httpServletRequest);
    }
}
