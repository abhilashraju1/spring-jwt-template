package com.starter.template.user.dao.impl;


import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.starter.template.user.dao.UserRepository;
import com.starter.template.user.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean createUser(User user) {
		boolean status = true;
		Session session =  sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(Exception e) {
			status = false;
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}

	@Override
	public User findByUserName(String userName) {
		User user = new User();
		try(Session session =  sessionFactory.openSession()) {
			@SuppressWarnings("unchecked")
			Query<User> query = session.createQuery("from User where userName = :userName AND status = :status");
	 		query.setParameter("userName", userName);
	 		query.setParameter("status", 'Y');
	 		query.setCacheable(true);
	 		user = query.getSingleResult();
		}catch(NoResultException noResult) {
		}
		return user;
	}

}
