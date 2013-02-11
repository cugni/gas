package it.polito.ai.gas.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import it.polito.ai.gas.business.User;
import it.polito.ai.gas.business.UserType;

import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/users")
@Controller
@RooWebScaffold(path = "users", formBackingObject = User.class)
@RooWebJson(jsonObject = User.class)
public class UserController {
	
	@RequestMapping(params = "register-form", produces = "text/html")
    public String createRegisterForm(Model uiModel) {
        populateEditForm(uiModel, new User());
        return "users/register";
    }
	@RequestMapping(value="register",method = RequestMethod.POST, produces = "text/html")
	   public String register(@Valid User user, 
			   BindingResult bindingResult, Model uiModel,
			   HttpServletRequest httpServletRequest) {
		user.setApproved(false);
		user.setRole(UserType.ROLE_USER);
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return "users/register";
        }
        uiModel.asMap().clear();
        user.persist();
        return "redirect:/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }
}
