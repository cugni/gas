package it.polito.ai.gas.business;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooJpaActiveRecord(versionField = "", table = "proposal", finders = { "findProposalsByDelegate" })
public class Proposal implements InterceptPersist {

    @NotNull
    @ManyToOne
    private User delegate;

    public String toString() {
        return this.getProduct() + "->" + this.getEndDate().toString();
    }
}
