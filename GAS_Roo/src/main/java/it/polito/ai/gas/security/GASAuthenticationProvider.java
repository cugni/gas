package it.polito.ai.gas.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import it.polito.ai.gas.business.User;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

/* 
	TUTORIAL: http://sujitpal.blogspot.it/2010/07/ktm-customizing-roo-security.html
*/

@SuppressWarnings("deprecation")
public class GASAuthenticationProvider extends 
AbstractUserDetailsAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
	    String password = (String) authentication.getCredentials();
	    if (!StringUtils.hasText(password)) {
	      throw new BadCredentialsException("Please enter password");
	    }

	    Query query = User.findUsersByUsernameEquals(username);
	    User found = (User) query.getSingleResult();
		
	    if (!found.getPassword().equals(password))
	          throw new BadCredentialsException("Invalid Password");

	    if (!found.getApproved())
	          throw new BadCredentialsException("User not approved");

	    // Login OK!
	    
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    switch(found.getRole())
	    {
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
	    
	    return new org.springframework.security.core.userdetails.User(
	    					username, password, authorities);
	}

}
