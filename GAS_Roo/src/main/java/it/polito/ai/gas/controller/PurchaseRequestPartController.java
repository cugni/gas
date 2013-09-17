package it.polito.ai.gas.controller;
import it.polito.ai.gas.business.PurchaseRequestPart;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/purchaserequestparts")
@Controller
@RooWebScaffold(path = "admin/purchaserequestparts", formBackingObject = PurchaseRequestPart.class)
public class PurchaseRequestPartController {



}
