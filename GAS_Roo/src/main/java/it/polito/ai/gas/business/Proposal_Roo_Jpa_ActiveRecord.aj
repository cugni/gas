// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.Proposal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Proposal_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Proposal.entityManager;
    
    public static final EntityManager Proposal.entityManager() {
        EntityManager em = new Proposal().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Proposal.countProposals() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Proposal o", Long.class).getSingleResult();
    }
    
    public static List<Proposal> Proposal.findAllProposals() {
        return entityManager().createQuery("SELECT o FROM Proposal o", Proposal.class).getResultList();
    }
    
    public static Proposal Proposal.findProposal(Integer id) {
        if (id == null) return null;
        return entityManager().find(Proposal.class, id);
    }
    
    public static List<Proposal> Proposal.findProposalEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Proposal o", Proposal.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Proposal.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Proposal.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Proposal attached = Proposal.findProposal(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Proposal.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Proposal.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Proposal Proposal.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Proposal merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
