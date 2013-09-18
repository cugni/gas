package it.polito.ai.gas.business;
import javax.persistence.PrimaryKeyJoinColumn;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@PrimaryKeyJoinColumn(name = "id")
@RooJpaActiveRecord(versionField = "", table = "producer", finders = { "findProducersByDelegate" })
public class Producer extends User {

    @Override
    public String toString() {
        return this.getCompanyName();
    }
}
