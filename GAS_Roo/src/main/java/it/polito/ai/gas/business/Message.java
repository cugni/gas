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
@RooToString(excludeFields = { "events", "user", "order", "proposal" })
@RooJpaActiveRecord(versionField = "", table = "message", finders = { "findMessagesByOrder", "findMessagesByProposal" })
public class Message implements InterceptPersist {

    @Size(max = 2500)
    private String text;
}
