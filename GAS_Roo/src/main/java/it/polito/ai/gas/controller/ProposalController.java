package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Proposal;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/proposals")
@Controller
@RooWebScaffold(path = "proposals", formBackingObject = Proposal.class)
@RooWebJson(jsonObject = Proposal.class)
public class ProposalController {
}
