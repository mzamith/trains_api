package org.feup.trains.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;


import org.feup.trains.model.Account;
import org.feup.trains.model.Role;
import org.feup.trains.repository.AccountRepository;
import org.feup.trains.repository.RoleRepository;
import org.feup.trains.util.BCryptPasswordEncoderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
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
    
    @Autowired
    private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
        logger.info("> findByUsername");
        Account account = accountRepository.findByUsername(username);
        
        if (account == null) throw new UsernameNotFoundException("Username was not found");


        logger.info("< findByUsername");
        return new User(account.getUsername(), account.getPassword(), account.getRoles());
	}
	
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    public Account create(Account account) {
        logger.info("> createAccount");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (account.getId() != null) {
            // Cannot create Exam with specified ID value
            logger.error(
                    "Attempted to create a Exam, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
        
        //Make Sure account is OK and Encrypt Password    
        account = this.setUpAccount(account);


        Account savedAccount = accountRepository.save(account);

        logger.info("< createAccount");
        return savedAccount;
    }
	
    private Account setUpAccount(Account account){
                
        String pw = account.getPassword();
        
        BCryptPasswordEncoderUtil a = new BCryptPasswordEncoderUtil();
        account.setPassword(a.encode(pw));
        
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByCodeAndEffective("ROLE_USER", new Date()));
        
        account.setRoles(roles);
        
        return account;
    }

}
