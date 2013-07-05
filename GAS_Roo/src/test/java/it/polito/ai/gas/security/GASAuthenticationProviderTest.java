package it.polito.ai.gas.security;

import static org.junit.Assert.*;
import it.polito.ai.gas.business.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.test.annotation.Rollback;

public class GASAuthenticationProviderTest {
	GASAuthenticationProvider autp = new GASAuthenticationProvider();

	@Rollback(true)
	@Test
	public void testNotApproved() {
		User u = new User();
		u.setApproved(false);
		u.setName("test1");
		u.setPassword("pass1");
		u.persist();
		try {
			UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(
					"pass1", "pass2");

			autp.retrieveUser("test1", t);
			fail("Expected an BadCredentialsException");
		} catch (BadCredentialsException e) {
			assertTrue(e.equals("BadCredentialsException"));
		}
	}

	@Rollback(true)
	@Test
	public void testApproved() {
		User u = new User();
		u.setApproved(true);
		u.setName("test1");
		u.setPassword("pass1");
		u.persist();

		UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(
				"pass1", "pass2");

		autp.retrieveUser("test1", t);

	}

	@Test
	public void testRetrieveUserStringUsernamePasswordAuthenticationToken() {
		fail("Not yet implemented");
	}

}
