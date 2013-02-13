package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;

import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products")
@Controller
@RooWebScaffold(path = "products", formBackingObject = Product.class)
@RooWebJson(jsonObject = Product.class)
public class ProductController {
	
	
}
