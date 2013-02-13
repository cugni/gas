package it.polito.ai.gas.business;

import java.util.Calendar;
import static it.polito.ai.gas.business.EventType.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;


public aspect Event_Pointcut {
	
	pointcut interceptPersist(InterceptPersist obj) : 
		execution(void InterceptPersist.persist()) && this(obj);
	
	// EVENTI PERSIST
	after (InterceptPersist obj) : interceptPersist(obj) {		
		
		Event e = new Event();

		// setto la fonte (e.setUser() oppure e.setProduct() oppure ...)
		// setto i destinatari(e.setUsers())
		
        if (obj instanceof User) {
        	// Caso: Nuovo utente -> notifico agli admin
        	
        	// fonte
            e.setUser((User) obj);
            
            // destinatari
            TypedQuery<User>  q = User.findUsersByRole(UserType.ROLE_ADMIN);
            e.getUsers().addAll(q.getResultList());
            
            e.setType(NEW_USER); // da cambiare
        }
        
        if (obj instanceof Product) {
        	// Caso: Nuovo prodotto -> notifico ai delegati incaricati
        	
        	// fonte
        	e.setProduct((Product) obj);
        	
            // destinatari
            TypedQuery<User> q = User.findUsersByRole(UserType.ROLE_DELEGATE);
            HashSet<User> delegates = new HashSet<User>();
            for(User delegate : q.getResultList())
            {
            	if (delegate.getProducers().contains(
            			((Product) obj).getProducer()))
            		delegates.add(delegate);
            }
            
            e.setUsers(delegates);
            
            e.setType(NEW_PRODUCT); // da cambiare
        }
        
        if (obj instanceof Message) {
        	// Caso: Nuovo messaggio -> notifico ai collegati alla proposal
        	Message m=(Message)obj;
        	
        	
        	// fonte
        	e.setMessage((Message) obj);
           for(PurchaseRequest p :    m.getOrder().getPurchaseRequests())
            {
            	e.getUsers().add(p.getAcquirer());
            }
            
         
            
            e.setType(NEW_MESSAGE); // da cambiare
        }
        
        e.setDate(Calendar.getInstance().getTime());		
        
        e.persist();
	}

}
