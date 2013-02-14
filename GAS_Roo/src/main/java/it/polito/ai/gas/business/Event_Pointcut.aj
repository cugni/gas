package it.polito.ai.gas.business;

import static it.polito.ai.gas.business.EventType.*;

import java.util.Calendar;

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
        	 // destinatari
            TypedQuery<User>  q = User.findUsersByRole(UserType.ROLE_ADMIN);
            if(q.getResultList().size()==0)return;
        	// Caso: Nuovo utente -> notifico agli admin
        	
        	// fonte
            e.setUser((User) obj);
            e.getUsers().addAll(q.getResultList());            
            e.setType(NEW_USER); // da cambiare
        }
        
        if (obj instanceof Product) {
        	// Caso: Nuovo prodotto -> notifico ai delegati incaricati
        	
        	// fonte
        	 TypedQuery<User> q = User.findUsersByRole(UserType.ROLE_DELEGATE);
             if(q.getResultList().size()==0)return;
        	e.setProduct((Product) obj);
        	// destinatari
                  
            
            for(User delegate : q.getResultList())
            {
            	if (delegate.getProducers().contains(
            			((Product) obj).getProducer()))
            		e.getUsers().add(delegate);
            }
            
            
            
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
