package it.polito.ai.gas.controller;

import it.polito.ai.gas.Utils;
import it.polito.ai.gas.business.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

@RequestMapping("/messages")
@Controller
@RooWebScaffold(path = "messages", formBackingObject = Message.class)
@RooWebJson(jsonObject = Message.class)
public class MessageController {

    @RequestMapping( method = RequestMethod.POST, produces = "text/html")
    public String create(Message message, BindingResult bindingResult, Model uiModel,
                                           @RequestParam(value = "proposal", required = true) Integer proposalId,
                                           HttpServletRequest httpServletRequest)
    {
        message.setProposal(Proposal.findProposal(proposalId));

        message.setDate(Calendar.getInstance());
        message.setUser(Utils.getCurrentUser());

        uiModel.asMap().clear();
        message.persist();

        return "redirect:/user/proposals/"+proposalId;
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Message());
        return "messages/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("message", Message.findMessage(id));
        uiModel.addAttribute("itemId", id);
        return "messages/show";
    }



    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "proposal", required = true) Integer proposalId,
                       @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
            Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("messages", Message.findMessagesByProposalOrderedByDate(Proposal.findProposal(proposalId))
                        .setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Proposal.countProposals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        }

        else
            uiModel.addAttribute("messages",
                Message.findMessagesByProposalOrderedByDate(Proposal.findProposal(proposalId)).setMaxResults(10).getResultList());

        addDateTimeFormatPatterns(uiModel);
        return "messages/list";
    }


    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Message message, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, message);
            return "messages/update";
        }
        uiModel.asMap().clear();
        message.merge();
        return "redirect:/messages/" + encodeUrlPathSegment(message.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, Message.findMessage(id));
        return "messages/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Message message = Message.findMessage(id);
        message.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/messages";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("message_date_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, Message message) {
        uiModel.addAttribute("message", message);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("proposals", Proposal.findAllProposals());
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
