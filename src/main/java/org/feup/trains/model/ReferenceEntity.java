
package org.feup.trains.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

/**
 * The parent class for all reference entities (i.e. reference data as opposed
 * to transactional data).
 * 
 * 
 * @author Manuel Zamith
 */
@MappedSuperclass
public class ReferenceEntity implements Serializable  {

    /**
     * The default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The primary key identifier.
     */
    @Id
    private Long id;

    /**
     * The unique code value, sometimes used for external reference.
     */
    @NotNull
    private String code;

    /**
     * A brief description of the entity.
     */
    @NotNull
    private String label;

    /**
     * The ordinal value facilitates sorting the entities.
     */
    @NotNull
    private Integer ordinal;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }


}