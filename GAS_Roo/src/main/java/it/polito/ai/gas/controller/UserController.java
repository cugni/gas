package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.User;
import it.polito.ai.gas.business.UserType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @RequestMapping("{id}/success")
    public String success(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("user", User.findUser(id));
        uiModel.addAttribute("itemId", id);
        return "admin/users/success";
    }

    @RequestMapping(value = "register", produces = "text/html")
    public String createRegisterForm(Model uiModel) {
        populateEditForm(uiModel, new User());
        return "admin/users/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "text/html")
    public String register(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        user.setApproved(false);
        user.setRole(UserType.ROLE_USER);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "admin/users/register";
        }
        user.persist();
        return "redirect:/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest) + "/success";
    }
}
