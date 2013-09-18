package it.polito.ai.gas.business;

import flexjson.JSONSerializer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooJpaActiveRecord(versionField = "", table = "proposal", finders = { "findProposalsByDelegate", "findProposalsByProduct" })
public class Proposal implements InterceptPersist {

    @NotNull
    @ManyToOne
    private User delegate;

    public String toString() {
        return this.getProduct() + "->" + this.getEndDate().toString();
    }

    public static List<it.polito.ai.gas.business.Proposal> findProposalEntriesByDelegate(User delegate, int firstResult, int maxResults) {
        EntityManager em = Product.entityManager();
        TypedQuery<Proposal> q = em.createQuery("SELECT o FROM Proposal o WHERE o.delegate = :delegate", Proposal.class);
        q.setParameter("delegate", delegate);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public String toJson() {
        return new JSONSerializer().include("id", "product").exclude("class").serialize(this);
    }
}
