/*package com.service.manager.user.configuration.security;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

*//**
 * User token authentication provider class.
 * 
 * @author smorwadkar
 *
 *//*
@Service
public class UserTokenAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userService;

	*//**
	 * Authenticates user based on the username and token provided.
	 * 
	 * @param authentication contains user authentication information.
	 * @return UsernamePasswordAuthenticationToken
	 * @throws AuthenticationException
	 *//*
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		authentication.setAuthenticated(doAuthenticate(authentication));
		LOG.info(
				"User token authentication provider  Creds[{}] Princ[{}] Auth[{}]",
				authentication.getCredentials(), authentication.getPrincipal(),
				authentication.isAuthenticated());

		if (!authentication.isAuthenticated()) {
			throw new BadCredentialsException("Unable to authenticate");
		}
		return authentication;
	}

	*//**
	 * Checks if this AuthenticationProvider supports the indicated Authentication object. 
	 * 
	 * @param authentication contains user authentication information.
	 * @return true if this AuthenticationProvider supports the indicated Authentication object. 
	 *//*
	@Override
	public boolean supports(Class<?> authentication) {
		return UserToken.class.isAssignableFrom(authentication);
	}

	*//**
	 * Performs authentication by checking if user exists and token matches.
	 * 
	 * @param authentication
	 * @return true if user is authenticated.
	 *
	 *//*
	private boolean doAuthenticate(Authentication authentication) {
		Object credentials = authentication.getCredentials();
		Object principal = authentication.getPrincipal();
		SecurityUser user = userService.loadUserByUsername(principal.toString());
		if (user != null)
			LOG.debug("Recieved data for user ", user.getUsername());
		else
			LOG.debug("No user found");
		if (user == null || !user.getUsername().equalsIgnoreCase(principal.toString())) {
			throw new BadCredentialsException("Username not found.");
		}
		//TODO Token is currently hard coded. Ideally it should be created with UserID, timestamp, expire at, 
		// IP address of client etc. Base64 encoding should also be applied.
		return principal.equals(user.getUsername()) && credentials.equals("mjb40osj");
	}

}
*/