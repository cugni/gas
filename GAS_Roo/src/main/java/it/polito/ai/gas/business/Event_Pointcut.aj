package it.polito.ai.gas.business;

import static it.polito.ai.gas.business.EventType.NEW_DELIVERY;
import static it.polito.ai.gas.business.EventType.NEW_MESSAGE;
import static it.polito.ai.gas.business.EventType.NEW_PRODUCT;
import static it.polito.ai.gas.business.EventType.NEW_PROPOSAL;
import static it.polito.ai.gas.business.EventType.NEW_PURCHASE_REQUEST;
import static it.polito.ai.gas.business.EventType.NEW_USER;

import java.util.Calendar;
import java.util.HashSet;


import javax.persistence.Query;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import it.polito.ai.gas.Utils;

import com.google.common.collect.Sets;



public aspect Event_Pointcut {
	
	pointcut interceptPersist(InterceptPersist obj) : 
		execution(void InterceptPersist.persist()) && this(obj);
	
	// EVENTI PERSIST
	@Transactional
	after (InterceptPersist obj)
		 : interceptPersist(obj) {		
		
		Event e = new Event();
	 

		// setto la fonte (e.setUser() oppure e.setProduct() oppure ...)
		// setto i destinatari(e.setUsers())
		
        if (obj instanceof User) {
        	 // destinatari
            TypedQuery<User>  q = User.findUsersByRole(UserType.ROLE_ADMIN);
            if(q.getResultList().size()==0)return;
        	// Caso: Nuovo utente -> notifico agli admin
        	
        	// fonte
            e.setUser((User) obj);
            
            e.setUsers(new HashSet(q.getResultList() ));            
            e.setType(NEW_USER); // da cambiare
        }
        else if (obj instanceof Product) {
        	// Caso: Nuovo prodotto -> notifico ai delegati incaricati
        	
        	// fonte
        	 TypedQuery<User> q = User.findUsersByRole(UserType.ROLE_DELEGATE);
             if(q.getResultList().size()==0)return;
        	e.setProduct((Product) obj);
        	// destinatari
                  
            e.setUsers(Utils.merge(e.getUsers()));
            for(User delegate : q.getResultList())
            {
            	if (delegate.getProducers().contains(
            			((Product) obj).getProducer()))
            		e.getUsers().add(delegate);
            }
                        
            e.setType(NEW_PRODUCT); // da cambiare
        }
        else if (obj instanceof Message) {
        	// Caso: Nuovo messaggio -> notifico ai collegati alla proposal
        	Message m = (Message)obj;
        	// fonte
        	e.setMessage((Message) obj);
            
            //e.setUsers(Utils.merge(e.getUsers()));
        	if (m.getOrder() != null)
        	if (m.getOrder().getPurchaseRequests() != null)
	            for(PurchaseRequest p : m.getOrder().getPurchaseRequests())
	            	e.setUsers(Utils.merge(e.getUsers(), p.getAcquirer()));
            
            e.setType(NEW_MESSAGE);
        }
        else if (obj instanceof Proposal) {
        	e.setProposal((Proposal) obj);
        	e.setUsers(Utils.merge(e.getUsers(), User.findAllUsers()));
        	
        	e.setType(NEW_PROPOSAL);
        }
        else if (obj instanceof DeliveryWithdrawal) {
        	DeliveryWithdrawal dw = (DeliveryWithdrawal) obj;
        	for(PurchaseRequest pr : dw.getOrder().getPurchaseRequests())
        	{
        		e.getUsers().add(pr.getAcquirer());
        	}
        	
        	e.setType(NEW_DELIVERY);
        }
        else if (obj instanceof PurchaseRequest) {
        	PurchaseRequest pr = (PurchaseRequest) obj;
            
            e.setUsers(Utils.merge(e.getUsers()));
        	e.getUsers().add(pr.getProposal().getDelegate());
        	
        	e.setType(NEW_PURCHASE_REQUEST);
        }
        
        else {
        	throw new IllegalArgumentException("Couldn't find a match for the object.");
        }
        
        
        e.setDate(Calendar.getInstance());
        e.persist();
	}

}