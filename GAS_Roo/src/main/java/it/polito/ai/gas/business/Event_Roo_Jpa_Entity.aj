// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Event;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

privileged aspect Event_Roo_Jpa_Entity {
    
    declare @type: Event: @Entity;
    
    declare @type: Event: @Table(name = "event");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Event.id;
    
    public Integer Event.getId() {
        return this.id;
    }
    
    public void Event.setId(Integer id) {
        this.id = id;
    }
    
}