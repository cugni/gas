// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Event;
import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Product;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Event_Roo_DbManaged {
    
    @ManyToMany
    @JoinTable(name = "notification", joinColumns = { @JoinColumn(name = "event_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) })
    private Set<User> Event.users;
    
    @ManyToOne
    @JoinColumn(name = "delivery_withdrawal", referencedColumnName = "id")
    private DeliveryWithdrawal Event.deliveryWithdrawal;
    
    @ManyToOne
    @JoinColumn(name = "message", referencedColumnName = "id")
    private Message Event.message;
    
    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product Event.product;
    
    @ManyToOne
    @JoinColumn(name = "proposal", referencedColumnName = "id")
    private Proposal Event.proposal;
    
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User Event.user;
    
    @Column(name = "date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar Event.date;
    
    public Set<User> Event.getUsers() {
        return users;
    }
    
    public void Event.setUsers(Set<User> users) {
        this.users = users;
    }
    
    public DeliveryWithdrawal Event.getDeliveryWithdrawal() {
        return deliveryWithdrawal;
    }
    
    public void Event.setDeliveryWithdrawal(DeliveryWithdrawal deliveryWithdrawal) {
        this.deliveryWithdrawal = deliveryWithdrawal;
    }
    
    public Message Event.getMessage() {
        return message;
    }
    
    public void Event.setMessage(Message message) {
        this.message = message;
    }
    
    public Product Event.getProduct() {
        return product;
    }
    
    public void Event.setProduct(Product product) {
        this.product = product;
    }
    
    public Proposal Event.getProposal() {
        return proposal;
    }
    
    public void Event.setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
    
    public User Event.getUser() {
        return user;
    }
    
    public void Event.setUser(User user) {
        this.user = user;
    }
    
    public Calendar Event.getDate() {
        return date;
    }
    
    public void Event.setDate(Calendar date) {
        this.date = date;
    }
    
}
