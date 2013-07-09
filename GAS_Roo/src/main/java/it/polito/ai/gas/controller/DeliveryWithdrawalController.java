package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/deliverywithdrawals")
@Controller
@RooWebScaffold(path = "admin/deliverywithdrawals", formBackingObject = DeliveryWithdrawal.class)
@RooWebJson(jsonObject = DeliveryWithdrawal.class)
public class DeliveryWithdrawalController {
}
