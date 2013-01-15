// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.MessagePK;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect MessagePK_Roo_Identifier {
    
    declare @type: MessagePK: @Embeddable;
    
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date MessagePK.date;
    
    @Column(name = "user", nullable = false, length = 20)
    private String MessagePK.user;
    
    @Column(name = "order", nullable = false)
    private Integer MessagePK.order;
    
    public MessagePK.new(Date date, String user, Integer order) {
        super();
        this.date = date;
        this.user = user;
        this.order = order;
    }

    private MessagePK.new() {
        super();
    }

    public Date MessagePK.getDate() {
        return date;
    }
    
    public String MessagePK.getUser() {
        return user;
    }
    
    public Integer MessagePK.getOrder() {
        return order;
    }
    
}
