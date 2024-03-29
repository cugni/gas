// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Message;
import it.polito.ai.gas.business.Proposal;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Message_Roo_Finder {
    
    public static TypedQuery<Message> Message.findMessagesByProposal(Proposal proposal) {
        if (proposal == null) throw new IllegalArgumentException("The proposal argument is required");
        EntityManager em = Message.entityManager();
        TypedQuery<Message> q = em.createQuery("SELECT o FROM Message AS o WHERE o.proposal = :proposal", Message.class);
        q.setParameter("proposal", proposal);
        return q;
    }
    
}
