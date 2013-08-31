package it.polito.ai.gas.controller.producer;

import it.polito.ai.gas.business.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

@RequestMapping("/producer/products")
@Controller
@RooWebScaffold(path = "producer/products", formBackingObject = Product.class)
@RooWebJson(jsonObject = Product.class)
public class ProducerProductController {


    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, product);
            return "producer/products/create";
        }
        uiModel.asMap().clear();
        User currentUser =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        product.setProducer(Producer.findProducer(currentUser.getId()));
        product.persist();
        return "redirect:/producer/products/" + encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Product());
        return "producer/products/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("product", Product.findProduct(id));
        uiModel.addAttribute("itemId", id);
        return "producer/products/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        User currentUser =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Producer currentProducer = Producer.findProducer(currentUser.getId());

        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("products", Product.findProductEntriesByProducer(currentProducer, firstResult, sizeNo));
            float nrOfPages = (float) Product.countProducts() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            //uiModel.addAttribute("products", Product.findAllProducts());

            uiModel.addAttribute("products", Product.findProductsByProducer(currentProducer));
        }
        addDateTimeFormatPatterns(uiModel);
        return "producer/products/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, product);
            return "producer/products/update";
        }
        uiModel.asMap().clear();
        product.merge();
        return "redirect:/producer/products/" + encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        populateEditForm(uiModel, Product.findProduct(id));
        return "producer/products/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Integer id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Product product = Product.findProduct(id);
        product.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/producer/products";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("product_availablefrom_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("product_availableto_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, Product product) {
        uiModel.addAttribute("product", product);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("events", Event.findAllEvents());
        uiModel.addAttribute("producers", Producer.findAllProducers());
        uiModel.addAttribute("proposals", Proposal.findAllProposals());

        User currentUser =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        uiModel.addAttribute("producer", currentUser)       ;
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }

}
