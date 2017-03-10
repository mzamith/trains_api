package org.feup.trains.service;

import java.util.Collection;

import org.feup.trains.model.Account;
import org.feup.trains.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {
	
    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The Spring Data repository for Account entities.
     */
    @Autowired
    private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
        logger.info("> findByUsername");
        Account account = accountRepository.findByUsername(username);
        
        if (account == null) throw new UsernameNotFoundException("Username was not found");


        logger.info("< findByUsername");
        return new User(account.getUsername(), account.getPassword(), account.getRoles());
	}
	

}
