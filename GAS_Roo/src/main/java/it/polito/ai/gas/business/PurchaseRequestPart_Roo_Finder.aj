// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package it.polito.ai.gas.business;

import it.polito.ai.gas.business.PurchaseRequestPart;
import it.polito.ai.gas.business.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect PurchaseRequestPart_Roo_Finder {
    
    public static TypedQuery<PurchaseRequestPart> PurchaseRequestPart.findPurchaseRequestPartsByAcquirer(User acquirer) {
        if (acquirer == null) throw new IllegalArgumentException("The acquirer argument is required");
        EntityManager em = PurchaseRequestPart.entityManager();
        TypedQuery<PurchaseRequestPart> q = em.createQuery("SELECT o FROM PurchaseRequestPart AS o WHERE o.acquirer = :acquirer", PurchaseRequestPart.class);
        q.setParameter("acquirer", acquirer);
        return q;
    }
    
}
