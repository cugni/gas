// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Message;
import java.util.Calendar;

privileged aspect Message_Roo_JavaBean {
    
    public String Message.getText() {
        return this.text;
    }
    
    public void Message.setText(String text) {
        this.text = text;
    }
    
    public Calendar Message.getDate() {
        return this.date;
    }
    
    public void Message.setDate(Calendar date) {
        this.date = date;
    }
    
}
