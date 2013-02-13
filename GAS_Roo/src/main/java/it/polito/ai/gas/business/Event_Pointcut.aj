package it.polito.ai.gas.business;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;


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
            Query q = User.findUsersByRole(UserType.ROLE_ADMIN);
            e.setUsers((Set<User>) q.getResultList());
            
            e.setType(0); // da cambiare
        }
        
        if (obj instanceof Product) {
        	// Caso: Nuovo prodotto -> notifico ai delegati incaricati
        	
        	// fonte
        	e.setProduct((Product) obj);
        	
            // destinatari
            Query q = User.findUsersByRole(UserType.ROLE_DELEGATE);
            HashSet<User> delegates = new HashSet<User>();
            for(User delegate : (Set<User>) q.getResultList())
            {
            	if (delegate.getProducers().contains(
            			((Product) obj).getProducer()))
            		delegates.add(delegate);
            }
            
            e.setUsers(delegates);
            
            e.setType(0); // da cambiare
        }
        
        if (obj instanceof Message) {
        	// Caso: Nuovo messaggio -> notifico ai collegati alla proposal
        	
        	// fonte
        	e.setMessage((Message) obj);
            
            // destinatari
        	HashSet<User> users = new HashSet<User>();
        	
            for(User user : User.findAllUsers())
            {
            	for(PurchaseRequest pr : user.getPurchaseRequests())
            		if (((Message) obj).getOrder() == pr.getProposal())
            			users.add(user);
            }
            
            e.setUsers(users);
            
            e.setType(0); // da cambiare
        }
        
        e.setDate(Calendar.getInstance().getTime());		
        
        e.persist();
	}

}
