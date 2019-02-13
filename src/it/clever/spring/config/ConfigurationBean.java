/**
 * ConfigurationBean.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 nov 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.clever.spring.aop.LogServiceAspect;
import it.clever.spring.utils.DatabaseConfigBean;

/**
 * @author robgion
 *
 */
@Configuration("configBean")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "it.clever.spring.*"})
@PropertySources({ @PropertySource("classpath:/spring/database.properties") })
public class ConfigurationBean {

	/** Parametri connessione database. */
	@Value("${database.url}")
	private String databaseUrl;
	
	@Value("${database.username}")
	private String databaseUsername;
	
	@Value("${database.password}")
	private String databasePassword;
	
	@Value("${database.driverClassName}")
	private String databaseDriverClassName;
	
	@Value("${jpa.persistence.unit}")
	private String persistenceUnitName;
	
	@Value("${hibernate.show_sql}")
	private String showSql;

	@Value("${hibernate.dialect}")
	private String databaseDialect;

	@Bean("puntamentiWsCDM")
	public WSData getWsData() {
		WSData ws = new WSData();
		ws.setWebserviceUrl(databaseUrl);
		return ws;
	}
	
	@Bean("puntamentiWsSOS")
	public WSData getWsDataSos() {
		WSData ws = new WSData();
		ws.setWebserviceUrl(persistenceUnitName);
		return ws;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		// Specifico qual'è la persistence unit
		em.setPersistenceUnitName(persistenceUnitName);

		// Setto tutte le info per la connession
		em.setDataSource(dataSource());
		
		em.setPackagesToScan(new String[] { "it.clever.spring.entities" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean(name = "datasource")
	public DatabaseConfigBean getDatabaseConfigurationBean() {
		DatabaseConfigBean datasource = new DatabaseConfigBean();
		datasource.setDatabaseDriver(databaseDriverClassName);
		datasource.setDatabasePassword(databasePassword);
		datasource.setDatabaseUsername(databaseUsername);
		datasource.setDatabaseUrl(databaseUrl);
		datasource.setPesistenceUnitName(persistenceUnitName);
		return datasource;
	}

	/**
	 * Metodo per la costruzione del DataSource per l'inzializzazione delle
	 * connessioni JPA.
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseDriverClassName);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}

	/**
	 * Metodo per il recupero delle informazioni legate ad Hibernate.
	 * 
	 * @return
	 */
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", databaseDialect);
		properties.setProperty("hibernate.show_sql", showSql);
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager trxManager = new JpaTransactionManager();
		trxManager.setEntityManagerFactory(emf);
		return trxManager;
	}
	
	@Bean public LogServiceAspect logServiceAspect() {
		return new LogServiceAspect();
	}
}
