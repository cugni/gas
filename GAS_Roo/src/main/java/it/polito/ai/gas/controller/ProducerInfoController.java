package it.polito.ai.gas.controller;

import it.polito.ai.gas.business.Producer;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/producerinfoes")
@Controller
@RooWebScaffold(path = "producerinfoes", formBackingObject = Producer.class)
@RooWebJson(jsonObject = Producer.class)
public class ProducerInfoController {
}
