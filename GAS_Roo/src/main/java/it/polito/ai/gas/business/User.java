package it.polito.ai.gas.business;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJson
@Inheritance(strategy = InheritanceType.JOINED)
@RooJpaActiveRecord(versionField = "", table = "user", finders = { "findUsersByUsernameEquals", "findUsersByApprovedNot", "findUsersByRole" })
public class User implements InterceptPersist {

    @Enumerated
    private UserType role;

    public static TypedQuery<it.polito.ai.gas.business.User> findUserNotNotified(Event e) {
        EntityManager em = User.entityManager();
        TypedQuery<User> q = em.createQuery("SELECT o FROM User AS " + "o WHERE o.id not in (select user_id from notification where event_id :event)", User.class);
        q.setParameter("event", e.getId());
        return q;
    }

}
