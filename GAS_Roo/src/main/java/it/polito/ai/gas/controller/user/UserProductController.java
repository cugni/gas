package it.polito.ai.gas.controller.user;

import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user/products")
@Controller
@RooWebScaffold(path = "user/products", formBackingObject = Product.class)
@RooWebJson(jsonObject = Product.class)
public class UserProductController {

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("product", Product.findProduct(id));
        uiModel.addAttribute("itemId", id);
        return "user/products/show";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("product_availablefrom_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("product_availableto_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

}
