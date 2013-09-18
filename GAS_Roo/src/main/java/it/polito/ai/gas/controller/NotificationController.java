package it.polito.ai.gas.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.polito.ai.gas.Utils;
import it.polito.ai.gas.business.*;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;

@RequestMapping("/notification")
@Controller
public class NotificationController {

    @RequestMapping(produces = "text/html")
    public String list(Model uiModel) {
   Object u= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(u instanceof User)   {
        User user  =
                (User) u;
   uiModel.addAttribute("events", Event.findEventsByUsers(Sets.newHashSet(user)).getResultList());

        addDateTimeFormatPatterns(uiModel);
        }else{
            uiModel.addAttribute("events", Lists.newArrayList());
            addDateTimeFormatPatterns(uiModel);

        }
        return "/notification/list";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        Event event = Event.findEvent(id);
        String view_parameter = event.getCauseType();
        UserType u= Utils.getCurrentUser().getRole();
        String sez;
        Integer idCause =event.getCauseId();
        switch (u){
            case ROLE_ADMIN:
                if(event.getType().equals(EventType.NEW_USER)){
                    return "redirect:/admin/users/approve";
                }
                sez="admin";
                break;
            case ROLE_DELEGATE:
                if(view_parameter.equals("proposals")
                        &&event.getProposal()
                        .getDelegate().equals(Utils.getCurrentUser() )){
                  return "redirect:/delegate/proposals/"+idCause;
                }
                //altrimenti il delegato è come un utente normale.
            case ROLE_USER:
                sez="user";
                break;
            case ROLE_PRODUCER:
                sez="producer";
                break;
            default:
                throw new IllegalStateException("That role doesn't exist!");


        }

        return "redirect:/"+sez+"/"+view_parameter+"/"+idCause;
    }


    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("event_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
}
