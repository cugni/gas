package it.polito.ai.gas.business;
import flexjson.JSONSerializer;
import java.util.Calendar;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "events", "user", "order", "proposal" })
@RooJpaActiveRecord(versionField = "", table = "message", finders = { "findMessagesByOrder", "findMessagesByProposal" })
public class Message implements InterceptPersist {

    @Size(max = 2500)
    private String text;

    @Column(name = "date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    public Calendar date;

    public static TypedQuery<Message> findMessagesByProposalOrderedByDate(Proposal proposal) {
        if (proposal == null) throw new IllegalArgumentException("The proposal argument is required");
        EntityManager em = Message.entityManager();
        TypedQuery<Message> q = em.createQuery("SELECT o FROM Message AS o WHERE o.proposal = :proposal ORDER BY o.date DESC", Message.class);
        q.setParameter("proposal", proposal);
        return q;
    }

    public String toJson() {
        return new JSONSerializer().include("id", "user", "proposal", "text").exclude("date", "*.class").serialize(this);
    }
}
