// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.EventType;

privileged aspect Event_Roo_JavaBean {
    
    public EventType Event.getType() {
        return this.type;
    }
    
    public void Event.setType(EventType type) {
        this.type = type;
    }
    
}
