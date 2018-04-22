package com.starter.template.user.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starter.template.user.dao.UserRolesRepository;
import com.starter.template.user.model.Role;

@Repository
public class UserRolesRepositoryImpl implements UserRolesRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<String> findRoleByUserName(String userName) {
		try(Session session = sessionFactory.openSession()){
			
			@SuppressWarnings("unchecked")
			NativeQuery<String> query = session.createNativeQuery("select UR.name from USER_DETAILS U, USER_ROLE_MAPPER URM, USER_ROLES UR where URM.USER_ID = U.userId and UR.roleId = URM.ROLE_ID and U.username = ?");
			query.setParameter(1, userName);
			query.setCacheable(true);
			return query.list();
		}catch (Exception e) {
			
		}
		return Collections.emptyList();
	}

	@Override
	public List<Role> findRolesByNames(String roleNames) {
		try(Session session = sessionFactory.openSession()){
			
			@SuppressWarnings("unchecked")
			Query<Role> query = session.createQuery("from Role where name IN(:names)");
			query.setParameter("names", roleNames);
			query.setCacheable(true);
			return  query.list();
		}catch (Exception e) {
			
		}
		return Collections.emptyList();
	}
	
	
}
