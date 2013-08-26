package it.polito.ai.gas.controller.delegate;

import it.polito.ai.gas.business.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
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

@RequestMapping("/delegate/proposals")
@Controller
@RooWebScaffold(path = "delegate/proposals", formBackingObject = Proposal.class)
public class DelegateProposal {
    public Proposal checkRights(Proposal proposal){
        User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(proposal.getDelegate()))
            throw new SecurityException("A delegate  can only modify his own proposal");
        return proposal;
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("proposal", Proposal.findProposalsByDelegate(user).getResultList());
        uiModel.addAttribute("itemId", id);
        return "delegate/proposals/show";
    }
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("proposal_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("proposal_enddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
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
        return "delegate/proposals/list";
    }
    void populateEditForm(Model uiModel, Proposal proposal) {
        uiModel.addAttribute("proposal", proposal);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("messages", Message.findAllMessages());
        uiModel.addAttribute("products", Product.findAllProducts());

    }
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Proposal proposal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        checkRights(proposal);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, proposal);
            return "delegate/proposals/update";
        }
        User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        proposal.setDelegate(user);

        uiModel.asMap().clear();
        proposal.merge();
        return "redirect:/delegate/proposals/" + encodeUrlPathSegment(proposal.getId().toString(), httpServletRequest);
    }
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel,checkRights(Proposal.findProposal(id)));
        return "delegate/proposals/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Proposal proposal = checkRights(Proposal.findProposal(id));
        proposal.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/delegate/proposal";
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
