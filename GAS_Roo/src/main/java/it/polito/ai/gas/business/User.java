package it.polito.ai.gas.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@RooJavaBean
@RooToString
@RooDbManaged(automaticallyDelete = true)
@RooJson
@Inheritance(strategy = InheritanceType.JOINED)
@RooJpaActiveRecord(versionField = "", 
table = "user", finders = { "findUsersByUsernameEquals", "findUsersByApprovedNot", "findUsersByRole" })
public class User implements InterceptPersist, UserDetails {

    @Enumerated
    private UserType role;

    public static TypedQuery<it.polito.ai.gas.business.User> findUserNotNotified(Event e) {
        EntityManager em = User.entityManager();
        TypedQuery<User> q = em.createQuery("SELECT o FROM User AS " + "o WHERE o.id not in (select user_id from notification where event_id :event)", User.class);
        q.setParameter("event", e.getId());
        return q;
    }

	 

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    switch(this.getRole())	    {
	    	case ROLE_DELEGATE: // User
	    		authorities.add(new GrantedAuthorityImpl("ROLE_DELEGATE"));
	    		 
	    	case ROLE_USER: // Delegate
	            authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
	          
	    		break;
	    	case ROLE_PRODUCER: // Producer
	            authorities.add(new GrantedAuthorityImpl("ROLE_PRODUCER"));
	    		break;
	    	case ROLE_ADMIN: // Admin
	    		// ? ->
	            authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
	            authorities.add(new GrantedAuthorityImpl("ROLE_DELEGATE"));
	            authorities.add(new GrantedAuthorityImpl("ROLE_PRODUCER"));
	            // <- ?
	            authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
	    		break;
	    	default:
	    		break;
	    }
	    return authorities;
	}

	 

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	/***
	 * Le nostre password non scadono mai. 
	 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return this.getApproved();
	}
	public String toString(){
		return this.getUsername();
	}

	 

	 
 
}
