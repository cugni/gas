package it.polito.ai.gas.business;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "event")
@RooDbManaged(automaticallyDelete = true)
public class Event {

    @Column(name = "type")
    @NotNull
    private EventType type;

    public String toString() {
        return type.name() + ":" + this.getMessage() + "-" + this.getDate().toString();
    }
}
