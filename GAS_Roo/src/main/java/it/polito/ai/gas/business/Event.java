package it.polito.ai.gas.business;

import flexjson.JSONSerializer;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

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

    
    public String toJson() {
        return new JSONSerializer().include("id", "type",
                "users",
                "deliveryWithdrawal",
                "message",
                "product",
                "proposal",
                "user").exclude("*").serialize(this);
    }

    public static TypedQuery<Event> findEventsByUser(User user) {
        if (user == null) throw new IllegalArgumentException("The user argument is required");
        EntityManager em = Event.entityManager();
        TypedQuery<Event> q = em.createQuery("SELECT o FROM Event AS o WHERE o.user = :user ORDER BY o.date DESC", Event.class);
        q.setParameter("user", user);
        return q;
    }

    public static TypedQuery<it.polito.ai.gas.business.Event> findEventsByUsers(Set<it.polito.ai.gas.business.User> users) {
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

    public Integer getCauseId() {
        if (this.getProposal() != null) return this.getProposal().getId();
        if (this.getDeliveryWithdrawal() != null) {
            return this.getDeliveryWithdrawal().getId();
        }
        if (this.getMessage() != null) {
            return this.getMessage().getId();
        }
        if (this.getProduct() != null) {
            return this.getProduct().getId();
        }
        if (this.getUser() != null) {
            return this.getUser().getId();
        }
        throw new IllegalStateException("At least one of proposal,delivery withdrawal," + " message, product or user should be different from null");
    }

    public Object getCauseObject() {
        if (this.getProposal() != null) return this.getProposal();
        if (this.getDeliveryWithdrawal() != null) {
            return this.getDeliveryWithdrawal();
        }
        if (this.getMessage() != null) {
            return this.getMessage();
        }
        if (this.getProduct() != null) {
            return this.getProduct();
        }
        if (this.getUser() != null) {
            return this.getUser();
        }
        throw new IllegalStateException("At least one of proposal,delivery withdrawal," + " message, product or user should be different from null");
    }

    public String getCauseType() {
        // le S...
        if (this.getUser() != null) return "users"; else if (this.getProposal() != null) return "proposals"; else if (this.getDeliveryWithdrawal() != null) return "deliverywithdrawals"; else if (this.getMessage() != null) return "messages"; else if (this.getProduct() != null) return "products";
        throw new IllegalStateException("At least one of proposal,delivery withdrawal," + " message, product or user should be different from null");
    }

    @Override
    public String toString() {
        return this.getType() + ": " + this.getCauseObject().toString();
    }
}
