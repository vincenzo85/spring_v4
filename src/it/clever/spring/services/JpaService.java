/**
 * JpaService.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 lug 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.services;

import javax.persistence.EntityManager;

/**
 * @author robgion
 *
 */
public interface JpaService {

	public EntityManager getDatabaseConnection();
	
	public void terminateService();
}
