package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Event;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/events")
@Controller
@RooWebScaffold(path = "admin/events", formBackingObject = Event.class)
public class EventController {

}
