package it.polito.ai.gas.web;

import it.polito.ai.gas.business.Proposal;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/proposals")
@Controller
@RooWebScaffold(path = "proposals", formBackingObject = Proposal.class)
public class ProposalController {
}
