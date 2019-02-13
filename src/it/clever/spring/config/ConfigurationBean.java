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
import javax.persistence.EntityTransaction;
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
import it.clever.spring.entities.Order;
import it.clever.spring.utils.DatabaseConfigBean;





/**
 * @author robgion
 * 
 * 
 * abbiamo la possibilità di iniettare i dao 
 * @autovired dell' entity manager non si può fare
 * 
 *
 */


@Configuration("configBean")
@EnableTransactionManagement
@EnableAspectJAutoProxy
// cerca le annotation .. nei vari pacchetti ... crea dei bean ove richiesto se prototipe ... lo crea alla richiesta...

@ComponentScan(basePackages = { "it.clever.spring.*"})

// configurazioni parametriche del....db... si carica in memoria le properties.... 
// devo replicare con le annotation tutto quello che c'era nell'xml...
// configurazione di startup ...è fatta me le sono caricate adesso le utilizzo...
@PropertySources({ @PropertySource("classpath:/spring/database.properties") })
public class ConfigurationBean {

	/** Parametri connessione database. */
	// lo posso mettere dentro un file di property esterno .... le connessioni al dv il driver la persistent unit di jpa... 
	// più altre configurazioni che mettevamo nel xml
	// quello di nuovo è la classe configurationBean 
	
	// deve essere connotata con @Configuration ... potrei farne l'autowired ma mi serve solo allo startup ... 
	// se gliela passassi potrei comprometterla ... attraverso i setter ad esempio...
	
	
	// nell'espression language va a vedere il contenuto del file property e lo usa .... per lo startup...
	// me li devo fare passare da fuori per fare dei set nelle classi di spring....
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
	
	@Value("S{jpa.entity}")
	private String entityjpa;

	@Bean("puntamentiWsCDM")
	public WSData getWsData() {
		WSData ws = new WSData();
		ws.setWebserviceUrl(databaseUrl);
		return ws;
	}
	
	
	// posso creami degli oggetti miei ... ho simulato dal file di property per invocare url dall'esterno ... 
	// se li delocalizzo nel file di property me li rilegge allo startup mi crea delle classi (dei bean ... ) ...
	// url di servizi remoti... sono dei java bean ... 
	// non sono annotati...
	@Bean("puntamentiWsSOS")
	
	// non bisogna fare la new di una classe annotata... questa è una classe non annotata...  se guaardo WSDAta....
	// adesso è chiamata puntamentiWsSOS.... 
	
	public WSData getWsDataSos() {
		WSData ws = new WSData();
		ws.setWebserviceUrl(persistenceUnitName);
		return ws;
	}
	
	// posso crearmi il jpa utils ...? .... 
	// iniettarlo dentro i dao.... 
	// no faccio qualcosa di meglio ... mi prendo solo un pezzetto... 
	
	
	// importanza dell'annotation bean, cosa fa? lo rende innietabile, messo in genere alle classi di configurazione...
	// va a vedere i metodi annotati con bean e li chiama... holliwood princible... li chiama lui ... 
	// li chiama spring ... hanno dei return .... passo al contesto ... eseguono codice e restituiscono un oggetto che permarrò nel 
	// contesto di spring ... 
	// ho simulato due instanze diverse.... questi li devo inittare .... 
	// nel controller devo fare l'injection di queste .... sono due instanza di questa classe  parla di puntamentiws riga 107 
	// riga 97.... 
	
	// potrei avere n ...file di configurazione .... fa vedere del progetto web Authorization security .... del progetto web...
	
	// le chiami allo startup.... 
	
	// la classe configuration ... servono per dire al contesto dell'applicazione per attivare tutte i metodi con bean.... 
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {

		// di questo faccio la new perchè non è annotata.... 
		// me ne accorgo perchè faccio la new,
		// me la creo. 
		// cosa fa questa ?
		// alimentata con dataSource... 
		// gli setto la persistentUnitName ...
		// gli setto il package to scan... 
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		// Specifico qual'è la persistence unit
		em.setPersistenceUnitName(persistenceUnitName);

		// Setto tutte le info per la connession
		em.setDataSource(dataSource());
		// le entity....  di cui fare lo scan... url delle classi....
		em.setPackagesToScan(new String[] { entityjpa  });
		// 
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
	// entity manager dentro i dao... 
	// gestire le transazione... 
	
	// dentro al dao v3 ... ho un tray catch per fare rollback .... 
	
//	public void save(Order order) {
//
//		EntityTransaction trx = null;
//		try {
//
//			trx = em.getTransaction();
//			trx.begin();
//
//			em.persist(order);
//			em.flush();
//
//			trx.commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			trx.rollback();
//		} finally {
//			if (em != null) {
//				em.close();
//			}
//
//		}
//	}
	
	// spring ci fornisce una classe che gestisce le transazioni in automatico...
	// rilasciare verso il contesto un instanza di platform transation manager factory...
	
	// transaction è un metodo di call back che non chiamiamo noi ...
	
	// ho già creato nel contesto l'entity manager factory...
	
	// mi garatisce che una finito lo startup nel contesto ho tutto quello che mi serve...
	
	
	// se devo fare una serie di operazioni sul db.... 
	
	// se voglio essere sicuro che tutto sia andato bene ... 
	
	// faccio una transazione.... 
	
	// se la cosa va male faccio la roolback per ripristinare la situazione iniziale...
	
	// le transazioni sono atomiche, una alla volta....
	
	// durante la transazione nessuno altro legge e scrive....
	
	// il db ha le sue regole.... 
	
	// il transaction manager del db ... 
	
	// myisam non gestisce la transazione
	
	// innodb gestisce la transazione... 
	
	// motore del db...
	
	// sql lite non gestisce le transazioni ... buono per applicazioni monoutente...
	
	// ho disabilitato lo spell checking ....
	
	
	
	/**
	 * @author Vix
	 * 
	 *    factory-> entity manager -> connesione 
	 * 
	 *
	 * 
	 * @param emf
	 * @return
	 */
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
