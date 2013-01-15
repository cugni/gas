package it.polito.ai.gas.web;

import it.polito.ai.gas.business.PurchaseRequest;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/purchaserequests")
@Controller
@RooWebScaffold(path = "purchaserequests", formBackingObject = PurchaseRequest.class)
public class PurchaseRequestController {
}
