package it.clever.spring.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import it.clever.spring.utils.DatabaseConfigBean;

/**
 * 
 * @author 2Clever
 *
 */
@Repository("jpaService")
@Scope(value="singleton")
public class JpaServiceImpl implements JpaService  {

	@Autowired(required = true)
	private DatabaseConfigBean datasource;

	@PersistenceUnit
	private EntityManagerFactory emfactory;

//	@PostConstruct
//	public void init() {
//		emfactory = Persistence.createEntityManagerFactory(datasource.getPesistenceUnitName());
//	}

	@Override
	public EntityManager getDatabaseConnection() {
		return emfactory.createEntityManager();
	}

	@PreDestroy
	@Override
	public void terminateService() {
		emfactory.close();
	}
}
