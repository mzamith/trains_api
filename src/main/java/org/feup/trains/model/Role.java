package org.feup.trains.model;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * The Role class is an entity model object. A Role describes a privilege level
 * within the application. A Role is used to authorize an Account to access a
 * set of application resources.
 * 
 * @author Manuel Zamith
 */
@Entity
public class Role extends ReferenceEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    public Role() {

    }

	@Override
	public String getAuthority() {
		return this.getCode();
	}
    

}