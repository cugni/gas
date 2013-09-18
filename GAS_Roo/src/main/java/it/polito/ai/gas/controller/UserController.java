package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.User;
import it.polito.ai.gas.business.UserType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

@RequestMapping("/admin/users")
@Controller
@RooWebScaffold(path = "admin/users", formBackingObject = User.class)
@RooWebJson(jsonObject = User.class)
public class    UserController {

    @RequestMapping(value = "approve", produces = "text/html")
    public String approveForm(Model uiModel) {
        Query query = User.findUsersByApprovedNot(true);
        uiModel.addAttribute("users", query.getResultList());
        addDateTimeFormatPatterns(uiModel);
        return "admin/users/approve";
    }

    @RequestMapping(value = "approve/{id}", produces = "text/html")
    public String approve(@PathVariable("id") Integer id, Model uiModel) {
        User user = User.findUser(id);
        user.setApproved(true);
        user.merge();
        uiModel.asMap().clear();
        uiModel.addAttribute("user", user);
        return "admin/users/approvesuccess";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("user_birthdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    private final ThreadLocal<SecureRandom> tsr=new ThreadLocal<SecureRandom>();

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "admin/users/create";
        }
        user.generateAuthToken();

        uiModel.asMap().clear();
        user.persist();
        return "redirect:/admin/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }

}
