// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Proposal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect Proposal_Roo_DbManaged {
    
    @OneToMany(mappedBy = "order")
    private Set<Message> Proposal.messages;
    
    @Column(name = "product")
    @NotNull
    private Integer Proposal.product;
    
    @Column(name = "start_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date Proposal.startDate;
    
    @Column(name = "end_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date Proposal.endDate;
    
    @Column(name = "min_reached")
    @NotNull
    private boolean Proposal.minReached;
    
    public Set<Message> Proposal.getMessages() {
        return messages;
    }
    
    public void Proposal.setMessages(Set<Message> messages) {
        this.messages = messages;
    }
    
    public Integer Proposal.getProduct() {
        return product;
    }
    
    public void Proposal.setProduct(Integer product) {
        this.product = product;
    }
    
    public Date Proposal.getStartDate() {
        return startDate;
    }
    
    public void Proposal.setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date Proposal.getEndDate() {
        return endDate;
    }
    
    public void Proposal.setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public boolean Proposal.isMinReached() {
        return minReached;
    }
    
    public void Proposal.setMinReached(boolean minReached) {
        this.minReached = minReached;
    }
    
}