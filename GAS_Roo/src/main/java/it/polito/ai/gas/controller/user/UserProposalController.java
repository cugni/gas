package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user/proposals")
@Controller
@RooWebScaffold(path = "user/proposals", formBackingObject = Proposal.class, update = false, delete = false, create = false)
public class UserProposalController {

}
