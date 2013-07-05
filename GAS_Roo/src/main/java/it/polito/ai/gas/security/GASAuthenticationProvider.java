package it.polito.ai.gas.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.polito.ai.gas.business.User;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/* 
	TUTORIAL: http://sujitpal.blogspot.it/2010/07/ktm-customizing-roo-security.html
*/

@SuppressWarnings("deprecation")
public class GASAuthenticationProvider extends 
AbstractUserDetailsAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails found,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		   if (!found.getPassword().equals(authentication.getCredentials()))
		          throw new BadCredentialsException("Username/password combination not valid");
		    if (!found.isEnabled())
		          throw new BadCredentialsException("User not yet approved");
		
	}

	@Override
	@Transactional(readOnly = true)
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
	    String password = (String) authentication.getCredentials();
	    if (!StringUtils.hasText(password)) {
	      throw new BadCredentialsException("Please enter password");
	    }
	    TypedQuery<User> qu=User.findUsersByUsernameEquals(username) ;
	    if(qu.getResultList().size()==0){
	    		throw new BadCredentialsException("Username/password combination not valid");
	    	}
	    	  User found = qu.getSingleResult();
	 
	    return found;
	}

}
