package it.polito.ai.gas.controller;


import com.google.common.collect.Sets;
import it.polito.ai.gas.Utils;
import it.polito.ai.gas.atmosphere.AtmosphereUtils;
import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.User;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class NotificationService
         {
    final static Logger l= Logger.getLogger(NotificationService.class.getSimpleName());

             /**
              *
              *
              * @param authToken auth token for the notification of the user
              */
             @RequestMapping(value="/ws/{authToken}", method = RequestMethod.GET)
             @ResponseBody
             public void websocket(final AtmosphereResource response,@PathVariable String authToken) throws IOException {
                    l.log(Level.INFO,"CALLED NOT {0} sent ",response.uuid());
                 User u=User.findUsersByAuthTokenEquals(authToken).getSingleResult();
                 if(u==null||!u.isEnabled())   {
                     throw new SecurityException("Token not found or user not enabled");
                 }



                 Broadcaster b = AtmosphereUtils.suspend(response);
             }

         }