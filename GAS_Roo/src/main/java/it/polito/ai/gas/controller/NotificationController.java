package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

@RequestMapping("/notification")
@Controller
public class NotificationController {

    @RequestMapping(produces = "text/html")
    public String list(Model uiModel) {
        User user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        HashSet<User> onlyme = new HashSet<User>();
        onlyme.add(user);

        uiModel.addAttribute("events", Event.findEventsByUsers(onlyme).getResultList());

        addDateTimeFormatPatterns(uiModel);
        return "/notification/list";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        User  user  =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("event", Event.findEvent(id));
        uiModel.addAttribute("itemId", id);

        /* dato che ogni evento puo' essere generato da un oggetto diverso,
        * nella view mostro solamente il campo settato
        * e non tutti gli altri messi a null.
        * view_parameter è la stringa del campo che mostro.
        * */
        Event event = Event.findEvent(id);
        String view_parameter = "";

        if (event.getUser() != null)
            view_parameter = "user";
         else if (event.getProposal() != null)
            view_parameter = "proposal";
        else if (event.getDeliveryWithdrawal() != null)
            view_parameter = "deliverywithdrawal";
        else if (event.getMessage() != null)
            view_parameter = "message";
        else if (event.getProduct() != null)
            view_parameter = "product";

        uiModel.addAttribute("view_parameter", view_parameter);
        uiModel.addAttribute("view_id", "c_it_polito_ai_gas_business_Event_"+view_parameter);

        return "/notification/show";
    }


    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("event_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
}
