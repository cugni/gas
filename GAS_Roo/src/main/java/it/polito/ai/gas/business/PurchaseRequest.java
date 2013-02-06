package it.polito.ai.gas.business;

import javax.persistence.ManyToOne;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "purchase_request")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class PurchaseRequest {

    @ManyToOne
    private Proposal proposal;

    @ManyToOne
    private User acquirer;
}
