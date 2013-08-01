package it.polito.ai.gas.business;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "delivery_withdrawal")
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooToString(excludeFields = { "events", "order", "collector" })
public class DeliveryWithdrawal implements InterceptPersist {
}
