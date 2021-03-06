package org.mo.open.common.entity;

import java.util.List;

/**
 * 用户权限
 * 
 * @author moziqi
 *
 */
public class Permission implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private java.lang.Long id;

	private java.lang.String name;

	private java.lang.String description;
	
	private List<Role> roles;
	
	public Permission() {
		super();
	}

	
	public Permission(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}


	public Permission(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
