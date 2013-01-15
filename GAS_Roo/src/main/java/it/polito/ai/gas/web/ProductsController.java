package it.polito.ai.gas.web;

import it.polito.ai.gas.business.Products;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/productses")
@Controller
@RooWebScaffold(path = "productses", formBackingObject = Products.class)
public class ProductsController {
}
