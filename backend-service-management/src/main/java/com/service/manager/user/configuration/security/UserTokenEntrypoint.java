package com.service.manager.user.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * Authentication entry point for token based authentication.
 * 
 * @author sdumbre
 * 
 */
public class UserTokenEntrypoint implements AuthenticationEntryPoint {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserTokenEntrypoint.class);

	/**
	 * Commences an authentication scheme. 
	 * Sets error code 401 in response.
	 * 
	 * @param request
	 * @param response
	 * @param authException
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String userName = Optional.fromNullable(request.getHeader("username")).or("");
		String userToken = Optional.fromNullable(request.getHeader("usertoken")).or("");
		LOG.info("Usertoken authentication entrypoint [{}] [{}]", userName, userToken);
		if(Strings.isNullOrEmpty(userToken)|| Strings.isNullOrEmpty(userName) || authException != null) {
			if(request.getSession() != null) {
				request.getSession().invalidate();
			}
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		}
	}


}
