/*package com.service.manager.user.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.service.manager.user.dto.UserDetails;
import com.service.manager.user.service.UserManagementService;

*//**
 * Service class for fetching user details.
 * 
 * @author sdumbre
 *//*
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


	@Autowired
	private UserManagementService userManagementService;

	*//**
	 * Locates the user based on the username.
	 * 
	 * @param username
	 *            user name
	 * @return SecurityUser object if user is found, null otherwise.
	 *//*
	@Override
	public SecurityUser loadUserByUsername(String username)
			throws UsernameNotFoundException {
		LOGGER.debug("Inside loadUserByUsername:" + username);
		final UserDetails userDetails = userManagementService
				.getUserById(username);
		return populateCustomUser(userDetails);
	}

	*//**
	 * Converts DashboardUser to SecurityUser.
	 * 
	 * @param userDetails
	 * @return SecurityUser object
	 *
	 *//*
	private SecurityUser populateCustomUser(UserDetails userDetails) {
		SecurityUser customUser = null;
		if (userDetails != null) {
			customUser = new SecurityUser();
			customUser.setUsername(userDetails.getUserId());
			customUser.setPassword(userDetails.getPassword());
			customUser.setEnabled(!userDetails.getDisabled());
			customUser.setEmail(userDetails.getEmail());
			customUser.setAccountNonLocked(!userDetails.getLocked());
			customUser.setPasswordFailures(userDetails.getPasswordFailures());
			customUser.setHidden(userDetails.getHidden());
			final Long lastLoginTime = userManagementService
					.getUserLastLoginTime(userDetails.getUserId());
			if (lastLoginTime == null) {
				customUser.setFirstLogin(true);
			}
		}
		return customUser;
	}

}
*/