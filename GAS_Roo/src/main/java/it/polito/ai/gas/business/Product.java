package it.polito.ai.gas.business;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "product")
@RooDbManaged(automaticallyDelete = true)
@RooJson
public class Product implements InterceptPersist {

    public String toString() {
        return this.getProducer().getName() + "-" + this.getName();
    }
}
