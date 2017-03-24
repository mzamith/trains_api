package org.feup.trains.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Configuration {
	
	@Id
	private Long id;
	
	@NotNull
	@Column(name = "conf_key")
	private String key;
	
	@NotNull
	@Column(name = "conf_value")
	private String value;

	public Configuration(){
		
	}
	
	public Configuration(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
