package it.polito.ai.gas.business;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooJpaActiveRecord(versionField = "", table = "purchase_request", finders = { "findPurchaseRequestsByAcquirer" })
@RooToString(excludeFields = { "proposal", "acquirer", "purchaseRequestParts" })
public class PurchaseRequest {

    @ManyToOne
    private Proposal proposal;

    @ManyToOne
    private User acquirer;

    /**
     */
    @NotNull
    private Boolean completed;
}
