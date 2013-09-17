package it.polito.ai.gas.business;

import org.junit.Test;
import org.springframework.roo.addon.dod.RooDataOnDemand;

@RooDataOnDemand(entity = PurchaseRequest.class)
public class PurchaseRequestDataOnDemand {
    @Test

    public void prova(){
        PurchaseRequest r = this.getRandomPurchaseRequest();
        r.persist();
    }

}
