// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.PurchaseRequest;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

privileged aspect PurchaseRequest_Roo_DbManaged {
    
    @Column(name = "proposal")
    @NotNull
    private Integer PurchaseRequest.proposal;
    
    @Column(name = "acquirer")
    @NotNull
    private Integer PurchaseRequest.acquirer;
    
    @Column(name = "quantity")
    @NotNull
    private Float PurchaseRequest.quantity;
    
    @Column(name = "received")
    private Boolean PurchaseRequest.received;
    
    public Integer PurchaseRequest.getProposal() {
        return proposal;
    }
    
    public void PurchaseRequest.setProposal(Integer proposal) {
        this.proposal = proposal;
    }
    
    public Integer PurchaseRequest.getAcquirer() {
        return acquirer;
    }
    
    public void PurchaseRequest.setAcquirer(Integer acquirer) {
        this.acquirer = acquirer;
    }
    
    public Float PurchaseRequest.getQuantity() {
        return quantity;
    }
    
    public void PurchaseRequest.setQuantity(Float quantity) {
        this.quantity = quantity;
    }
    
    public Boolean PurchaseRequest.getReceived() {
        return received;
    }
    
    public void PurchaseRequest.setReceived(Boolean received) {
        this.received = received;
    }
    
}
