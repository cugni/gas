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
@RooToString(excludeFields = { "proposal", "acquirer", "purchaseRequestParts" })
@RooJpaActiveRecord(versionField = "", table = "purchase_request", finders = { "findPurchaseRequestsByAcquirer", "findPurchaseRequestsByCompletedNot" })
public class PurchaseRequest {

    @ManyToOne
    private Proposal proposal;

    @ManyToOne
    private User acquirer;

    /**
     */
    @NotNull
    private Boolean completed;

    public Double getToMin(){
        Product p=this.getProposal().getProduct();
        double stock=p.getStockQuantity();
        double min=p.getMinToBuyUser();
        double rem=0;
        if(min>this.getQuantity()){
            rem=min-this.getQuantity();
        }

        if((this.getQuantity()+rem)%stock!=0){
              rem+= stock-(this.getQuantity()+rem)%stock;
        }
        return rem;
    }
}
