package org.feup.trains.repository;

import java.util.Collection;

import org.feup.trains.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The AccountRepository interface is a Spring Data JPA data repository for
 * Account entities. The AccountRepository provides all the data access
 * behaviors exposed by <code>JpaRepository</code> and additional custom
 * behaviors may be defined in this interface.
 * 
 * @author Manuel Zamith
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Query for a single Account entity by username. This method illustrates
     * the Query Method approach for query definition.
     * 
     * <pre>
     * SELECT a FROM Account a WHERE a.username = ?1
     * </pre>
     * 
     * @param username A String username value to query the repository.
     * @return An Account or <code>null</code> if none found.
     */
    Account findByUsername(String username);

}
