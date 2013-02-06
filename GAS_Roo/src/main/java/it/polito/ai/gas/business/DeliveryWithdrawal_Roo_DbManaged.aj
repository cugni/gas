// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.DeliveryWithdrawal;
import it.polito.ai.gas.business.Proposal;
import it.polito.ai.gas.business.User;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect DeliveryWithdrawal_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "id", nullable = false)
    private Proposal DeliveryWithdrawal.order;
    
    @ManyToOne
    @JoinColumn(name = "collector", referencedColumnName = "id")
    private User DeliveryWithdrawal.collector;
    
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date DeliveryWithdrawal.deliveryDate;
    
    @Column(name = "withdrawal_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date DeliveryWithdrawal.withdrawalDate;
    
    public Proposal DeliveryWithdrawal.getOrder() {
        return order;
    }
    
    public void DeliveryWithdrawal.setOrder(Proposal order) {
        this.order = order;
    }
    
    public User DeliveryWithdrawal.getCollector() {
        return collector;
    }
    
    public void DeliveryWithdrawal.setCollector(User collector) {
        this.collector = collector;
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
    
}
