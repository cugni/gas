package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/proposals")
@Controller
@RooWebScaffold(path = "proposals", formBackingObject = Proposal.class)
@RooWebJson(jsonObject = Proposal.class)
public class ProposalController {
	  @RequestMapping(params = "form&product={idp}", produces = "text/html")
	    public String createForm(@PathVariable("idp") Integer idp,Model uiModel) {
		  
		  Proposal p=new Proposal();
		  p.setProduct(Product.findProduct(idp));
	        populateEditForm(uiModel, p);
	        return "proposals/create";
	    }
}
