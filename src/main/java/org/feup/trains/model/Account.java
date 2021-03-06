
package org.feup.trains.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.feup.trains.model.TransactionalEntity;
import org.feup.trains.util.Luhn;

/**
 * The Account class is an entity model object. An Account describes the
 * security credentials and authentication flags that permit access to
 * application functionality.
 * 
 * @author Manuel Zamith
 */
@Entity
public class Account extends TransactionalEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * The primary key identifier.
     */
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "enabled")
    @NotNull
    private boolean enabled = true;

    @Column(name = "credentialsexpired")
    @NotNull
    private boolean credentialsexpired = false;

    @Column(name = "expired")
    @NotNull
    private boolean expired = false;

    @Column(name = "locked")
    @NotNull
    private boolean locked = false;
    
    @Column(name = "card_number")
    private String cardNumber;
    
    @Column(name = "card_date")
    private Date cardDate;


    @ManyToMany(
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "AccountRole",
            joinColumns = @JoinColumn(
                    name = "accountId",
                    referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(
                    name = "roleId",
                    referencedColumnName = "id") )
    private Set<Role> roles;

    public Account() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialsexpired() {
        return credentialsexpired;
    }

    public void setCredentialsexpired(boolean credentialsexpired) {
        this.credentialsexpired = credentialsexpired;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
   
    
    public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getCardDate() {
		return cardDate;
	}

	public void setCardDate(Date cardDate) {
		this.cardDate = cardDate;
	}
	
	public boolean hasValidCard(){
		
		if (this.cardDate == null || this.cardNumber == null) return false;
		
		return (Luhn.check(this.cardNumber) && this.cardDate.after(new Date()));
	}

	public boolean isInspector(){
    	
    	for (Role role: roles){
    		if (role.getCode().equals("ROLE_INSPECTOR")){
    			return true;
    		}
    	}
    	return false;
    }

}