package it.polito.ai.gas.web;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/deliverywithdrawals")
@Controller
@RooWebScaffold(path = "deliverywithdrawals", formBackingObject = DeliveryWithdrawal.class)
public class DeliveryWithdrawalController {
}
