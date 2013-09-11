package it.polito.ai.gas.business;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooDbManaged(automaticallyDelete = true)
@RooJson
@RooJpaActiveRecord(versionField = "", table = "message", finders = { "findMessagesByOrder" })
@RooToString(excludeFields = { "events", "user", "order", "proposal" })
public class Message implements InterceptPersist {

    @Size(max = 2500)
    private String text;
}
