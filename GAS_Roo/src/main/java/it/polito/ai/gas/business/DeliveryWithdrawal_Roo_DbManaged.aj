// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect DeliveryWithdrawal_Roo_DbManaged {
    
    @OneToMany(mappedBy = "deliveryWithdrawal", cascade = CascadeType.ALL)
    private Set<Event> DeliveryWithdrawal.events;
    
    @ManyToOne
    @JoinColumn(name = "collector", referencedColumnName = "id")
    private User DeliveryWithdrawal.collector;
    
    @ManyToOne
    @JoinColumn(name = "proposal", referencedColumnName = "id", nullable = false)
    private Proposal DeliveryWithdrawal.proposal;
    
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date DeliveryWithdrawal.deliveryDate;
    
    @Column(name = "withdrawal_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date DeliveryWithdrawal.withdrawalDate;
    
    @Column(name = "address", length = 90)
    private String DeliveryWithdrawal.address;
    
    public Set<Event> DeliveryWithdrawal.getEvents() {
        return events;
    }
    
    public void DeliveryWithdrawal.setEvents(Set<Event> events) {
        this.events = events;
    }
    
    public User DeliveryWithdrawal.getCollector() {
        return collector;
    }
    
    public void DeliveryWithdrawal.setCollector(User collector) {
        this.collector = collector;
    }
    
    public Proposal DeliveryWithdrawal.getProposal() {
        return proposal;
    }
    
    public void DeliveryWithdrawal.setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
    
    public Date DeliveryWithdrawal.getDeliveryDate() {
        return deliveryDate;
    }
    
    public void DeliveryWithdrawal.setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
    public Date DeliveryWithdrawal.getWithdrawalDate() {
        return withdrawalDate;
    }
    
    public void DeliveryWithdrawal.setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
    
    public String DeliveryWithdrawal.getAddress() {
        return address;
    }
    
    public void DeliveryWithdrawal.setAddress(String address) {
        this.address = address;
    }
    
}
