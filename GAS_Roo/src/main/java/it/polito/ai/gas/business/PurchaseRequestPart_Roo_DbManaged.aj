// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.PurchaseRequestPart;
import it.polito.ai.gas.business.User;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

privileged aspect PurchaseRequestPart_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "purchaseRequest", referencedColumnName = "id", nullable = false)
    private PurchaseRequest PurchaseRequestPart.purchaseRequest;
    
    @ManyToOne
    @JoinColumn(name = "acquirer", referencedColumnName = "id", nullable = false)
    private User PurchaseRequestPart.acquirer;
    
    public PurchaseRequest PurchaseRequestPart.getPurchaseRequest() {
        return purchaseRequest;
    }
    
    public void PurchaseRequestPart.setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }
    
    public User PurchaseRequestPart.getAcquirer() {
        return acquirer;
    }
    
    public void PurchaseRequestPart.setAcquirer(User acquirer) {
        this.acquirer = acquirer;
    }
    
}
