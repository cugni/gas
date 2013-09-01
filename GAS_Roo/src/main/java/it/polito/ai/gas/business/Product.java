package it.polito.ai.gas.business;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.List;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooJpaActiveRecord(versionField = "", table = "product", finders = { "findProductsByProducer" })
public class Product implements InterceptPersist {

    @ManyToOne
    @JoinColumn(name = "producer", referencedColumnName = "id", nullable = false)
    private Producer producer;

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Size(max = 500)
    private String description;

    public String toString() {
        return this.getProducer().getName() + "-" + this.getName();
    }

    public static List<Product> findProductEntriesByProducer(Producer producer, int firstResult, int maxResults) {
        EntityManager em = Product.entityManager();
        TypedQuery<Product> q = em.createQuery("SELECT o FROM Product AS o WHERE o.producer = :producer", Product.class);
        q.setParameter("producer", producer);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
