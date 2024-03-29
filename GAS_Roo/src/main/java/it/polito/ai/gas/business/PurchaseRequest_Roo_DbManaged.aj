// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.PurchaseRequest;
import it.polito.ai.gas.business.PurchaseRequestPart;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

privileged aspect PurchaseRequest_Roo_DbManaged {
    
    @OneToMany(mappedBy = "purchaseRequest", cascade = CascadeType.ALL)
    private Set<PurchaseRequestPart> PurchaseRequest.purchaseRequestParts;
    
    @Column(name = "quantity")
    @NotNull
    private Float PurchaseRequest.quantity;
    
    @Column(name = "received")
    @NotNull
    private boolean PurchaseRequest.received;
    
    public Set<PurchaseRequestPart> PurchaseRequest.getPurchaseRequestParts() {
        return purchaseRequestParts;
    }
    
    public void PurchaseRequest.setPurchaseRequestParts(Set<PurchaseRequestPart> purchaseRequestParts) {
        this.purchaseRequestParts = purchaseRequestParts;
    }
    
    public Float PurchaseRequest.getQuantity() {
        return quantity;
    }
    
    public void PurchaseRequest.setQuantity(Float quantity) {
        this.quantity = quantity;
    }
    
    public boolean PurchaseRequest.isReceived() {
        return received;
    }
    
    public void PurchaseRequest.setReceived(boolean received) {
        this.received = received;
    }
    
}
