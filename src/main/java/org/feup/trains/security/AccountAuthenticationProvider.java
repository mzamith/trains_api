package org.feup.trains.security;

import org.feup.trains.util.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * A Spring Security AuthenticationProvider which extends
 * <code>AbstractUserDetailsAuthenticationProvider</code>. This classes uses the
 * <code>AccountUserDetailsService</code> to retrieve a UserDetails instance.
 * 
 * A PasswordEncoder compares the supplied authentication credentials to those
 * in the UserDetails.
 * 
 * @author Manuel Zamith
 */
@Component
public class AccountAuthenticationProvider
        extends AbstractUserDetailsAuthenticationProvider {

    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * A Spring Security UserDetailsService implementation based upon the
     * Account entity model.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * A PasswordEncoder instance to hash clear test password values.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken token)
                    throws AuthenticationException {
        logger.debug("> additionalAuthenticationChecks");

        if (token.getCredentials() == null
                || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Credentials may not be null.");
        }

        if (! token.getCredentials().equals(
                userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        RequestContext.setUsername(userDetails.getUsername());

        logger.debug("< additionalAuthenticationChecks");
    }

    @Override
    public UserDetails retrieveUser(String username,
            UsernamePasswordAuthenticationToken token)
                    throws AuthenticationException {
        logger.debug("> retrieveUser");

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        logger.debug("< retrieveUser");
        return userDetails;
    }

}