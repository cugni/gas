package it.polito.ai.gas.controller.user;
import it.polito.ai.gas.business.PurchaseRequestPart;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user/purchaserequestparts")
@Controller
@RooWebScaffold(path = "user/purchaserequestparts", formBackingObject = PurchaseRequestPart.class)
public class UserPurchaseRequestPartController {
}
