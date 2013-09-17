package it.polito.ai.gas.business;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import flexjson.JSONSerializer;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Calendar;
import java.util.Set;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJpaActiveRecord(versionField = "", table = "event", finders = { "findEventsByUser", "findEventsByUsers" })
public class Event {

    @Column(name = "type")
    @NotNull
    private EventType type;

    @Column(name = "date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @org.springframework.format.annotation.DateTimeFormat(style = "MM")
    private Calendar date;

    public String toString() {
        return type.name() + ":" + this.getMessage() + "-" + this.getDate().toString();
    }

    public String toJson() {
        return new JSONSerializer().include("id", "type").exclude("date", "class").serialize(this);
    }

    public static TypedQuery<Event> findEventsByUser(User user) {
        if (user == null) throw new IllegalArgumentException("The user argument is required");
        EntityManager em = Event.entityManager();
        TypedQuery<Event> q = em.createQuery("SELECT o FROM Event AS o WHERE o.user = :user ORDER BY o.date DESC", Event.class);
        q.setParameter("user", user);
        return q;
    }

    public static TypedQuery<Event> findEventsByUsers(Set<User> users) {
        if (users == null) throw new IllegalArgumentException("The users argument is required");
        EntityManager em = entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Event AS o WHERE");
        for (int i = 0; i < users.size(); i++) {
            if (i > 0) queryBuilder.append(" AND");
            queryBuilder.append(" :users_item").append(i).append(" MEMBER OF o.users");
        }
        queryBuilder.append(" ORDER BY o.date DESC");
        TypedQuery<Event> q = em.createQuery(queryBuilder.toString(), Event.class);
        int usersIndex = 0;
        for (User _user : users) {
            q.setParameter("users_item" + usersIndex++, _user);
        }
        return q;
    }
}
