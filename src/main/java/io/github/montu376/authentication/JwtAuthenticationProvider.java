package io.github.montu376.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private Logger logger;

    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        logger.info("Username"+ username);
        logger.info("Password = "+ password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        logger.info("Username"+ userDetails.getUsername());
        logger.info("Password = "+ userDetails.getPassword());
        logger.info("Password matched"+ userDetails.getPassword().equals(password));

        if(userDetails.getPassword().equals(password)== false){
            authentication.setAuthenticated(false);
            throw new BadCredentialsException("Username or Password is Incorrect");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(JwtAuthentication.class);
    }

}
