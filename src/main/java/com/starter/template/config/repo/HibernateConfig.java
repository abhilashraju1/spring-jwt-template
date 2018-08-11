package com.starter.template.config.repo;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.starter.template.user.model.Address;
import com.starter.template.user.model.Role;
import com.starter.template.user.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.example.demo")
public class HibernateConfig {

	@Autowired @Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(hibernateProperties());
		sessionBuilder.addPackages("com.example.demo.user.model");
		sessionBuilder.addAnnotatedClasses(User.class, Role.class, Address.class);
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired @Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "update");
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
    	
    	properties.put("javax.persistence.validation.mode", "none");
    	
    	properties.put("hibernate.cache.use_second_level_cache", "false");
    	properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
    	properties.put("hibernate.cache.use_query_cache", "false");
    	
    	return properties;
	}
}
