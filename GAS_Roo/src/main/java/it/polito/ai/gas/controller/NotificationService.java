package it.polito.ai.gas.controller;


import com.google.common.collect.Sets;
import it.polito.ai.gas.Utils;
import it.polito.ai.gas.atmosphere.AtmosphereUtils;
import it.polito.ai.gas.business.Event;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
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
              * @param id a message of type T
              */
             @RequestMapping(value="/not/{id}", method = RequestMethod.GET)
             @ResponseBody
             public void websocket(final AtmosphereResource response,@PathVariable Integer id) throws IOException {
                    l.log(Level.INFO,"CALLED NOT {0} sent ",response.uuid());
//                 if(!Utils.getCurrentUser().getId().equals(id)){
//                throw      new SecurityException("You are trying to access to the notification stream of another user. ");
//                 }


                 Broadcaster b = AtmosphereUtils.suspend(response);
//                 if(!response.isResumed()){
//                     List<Event> evs = Event.findEventsByUsers(Sets.newHashSet(User.findUser(id))).setMaxResults(5).getResultList();
//                     for(Event e:evs){
//                         b.broadcast(e.toJson());
//                     }
//                 }
             }
             @RequestMapping(value="/not/lasts", method = RequestMethod.GET)
             @ResponseBody
             public String getLastNotification() throws JSONException {

                 List<Event> evs = Event.findEventsByUsers(Sets.newHashSet(Utils.getCurrentUser())).setMaxResults(5).getResultList();
                 if(evs.isEmpty())
                     return "[]";
                 StringBuilder sb=new StringBuilder("[");

                 for(Event e:evs){
                 sb.append(e.toJson()+",");
                 }
                 sb.setCharAt(sb.length()-1,']');

                 return sb.toString();
             }
         }