/**
 * DatabaseConfigBean.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 lug 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.utils;

/**
 * @author robgion
 * 
 * Classe per la propagazione dei parametri di connessione alla base dati.
 */
public class DatabaseConfigBean {

	private String databaseDriver;
	private String databaseUrl;
	private String databaseUsername;
	private String databasePassword;

	private String pesistenceUnitName;
	
	public String getDatabaseDriver() {
		return databaseDriver;
	}
	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}
	public String getDatabaseUrl() {
		return databaseUrl;
	}
	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}
	public String getDatabaseUsername() {
		return databaseUsername;
	}
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	public String getPesistenceUnitName() {
		return pesistenceUnitName;
	}
	public void setPesistenceUnitName(String pesistenceUnitName) {
		this.pesistenceUnitName = pesistenceUnitName;
	}
}
