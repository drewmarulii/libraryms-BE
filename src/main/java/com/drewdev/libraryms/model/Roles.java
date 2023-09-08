package com.drewdev.libraryms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_roles")
public class Roles {

	private String roleCode;
	private String roleName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
