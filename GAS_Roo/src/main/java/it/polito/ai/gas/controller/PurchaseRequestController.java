package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.PurchaseRequest;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/purchaserequests")
@Controller
@RooWebScaffold(path = "admin/purchaserequests", formBackingObject = PurchaseRequest.class)
@RooWebJson(jsonObject = PurchaseRequest.class)
public class PurchaseRequestController {
}
