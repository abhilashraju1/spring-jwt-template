package com.starter.template.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLES")
public class Role {
	@Id @GeneratedValue
	private long roleId;
	private String name;
	
	public Role() {}
	
	public Role(long roleId, String name) {
		this.roleId = roleId;
		this.name = name;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
