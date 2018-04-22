package com.starter.template.user.dao;

import java.util.Collections;
import java.util.List;

import com.starter.template.user.model.Role;

public interface UserRolesRepository {
	List<String> findRoleByUserName(String userName);
	
	default List<Role> findRolesById(long roleId){
		return Collections.emptyList();
	}
	
	default List<Role> findRolesByNames(String roleNames){
		return Collections.emptyList();
	}
}
