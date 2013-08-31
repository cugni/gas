package it.polito.ai.gas.business;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "purchase_request_part")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "purchaseRequest", "acquirer" })
public class PurchaseRequestPart {

    /**
     */
    private float quantity;
}
