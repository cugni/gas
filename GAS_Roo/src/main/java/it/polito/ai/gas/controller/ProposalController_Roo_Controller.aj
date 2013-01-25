// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.controller.ProposalController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ProposalController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String ProposalController.create(@Valid Proposal proposal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, proposal);
            return "proposals/create";
        }
        uiModel.asMap().clear();
        proposal.persist();
        return "redirect:/proposals/" + encodeUrlPathSegment(proposal.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String ProposalController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Proposal());
        return "proposals/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String ProposalController.show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("proposal", Proposal.findProposal(id));
        uiModel.addAttribute("itemId", id);
        return "proposals/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String ProposalController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
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
        return "proposals/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String ProposalController.update(@Valid Proposal proposal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, proposal);
            return "proposals/update";
        }
        uiModel.asMap().clear();
        proposal.merge();
        return "redirect:/proposals/" + encodeUrlPathSegment(proposal.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String ProposalController.updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, Proposal.findProposal(id));
        return "proposals/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String ProposalController.delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Proposal proposal = Proposal.findProposal(id);
        proposal.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/proposals";
    }
    
    void ProposalController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("proposal_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("proposal_enddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void ProposalController.populateEditForm(Model uiModel, Proposal proposal) {
        uiModel.addAttribute("proposal", proposal);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("messages", Message.findAllMessages());
    }
    
    String ProposalController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
