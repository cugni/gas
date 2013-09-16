package it.polito.ai.gas.controller;



import it.polito.ai.gas.atmosphere.AtmosphereUtils;
import it.polito.ai.gas.business.Event;
import org.atmosphere.cpr.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class NotificationService
         {
    final static Logger l= Logger.getLogger(NotificationService.class.getSimpleName());

             /**
              * Implement this method to get invoked every time a new {@link org.atmosphere.cpr.Broadcaster#broadcast(Object)}
              * occurs.
              *
              * @param message a message of type T
              */
             @RequestMapping(value="/not", method = RequestMethod.GET)
             @ResponseBody
             public void onMessage(final AtmosphereResource response) throws IOException {
                    l.log(Level.INFO,"CALLED NOT {0} sent ",response.uuid());
                 AtmosphereUtils.suspend(response);
             }
         }