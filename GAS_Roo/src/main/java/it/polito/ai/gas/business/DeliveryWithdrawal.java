package it.polito.ai.gas.business;

import flexjson.JSONSerializer;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooToString(excludeFields = { "events", "order", "collector", "proposal" })
@RooJpaActiveRecord(versionField = "", table = "delivery_withdrawal", finders = { "findDeliveryWithdrawalsByOrder", "findDeliveryWithdrawalsByProposal" })
public class DeliveryWithdrawal implements InterceptPersist {

    public String toJson() {
        return new JSONSerializer().include("id", "order", "deliveryWithdrawals").exclude("class").serialize(this);
    }
}
