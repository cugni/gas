package it.polito.ai.gas.business;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "purchaseRequest", "acquirer" })
@RooJpaActiveRecord(versionField = "", table = "purchase_request_part", finders = { "findPurchaseRequestPartsByAcquirer" })
public class PurchaseRequestPart {
}
