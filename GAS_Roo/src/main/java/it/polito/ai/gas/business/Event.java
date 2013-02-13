package it.polito.ai.gas.business;

import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "event")
@RooDbManaged(automaticallyDelete = true)
public class Event {

    public static TypedQuery<it.polito.ai.gas.business.User> findUserNotNotified(it.polito.ai.gas.business.Event e) {
        EntityManager em = User.entityManager();
        TypedQuery<User> q = em.createQuery("SELECT o FROM User AS " + "o WHERE o.id not in (select user_id from notification where event_id :event)", User.class);
        q.setParameter("event", e.getId());
        return q;
    }
}
