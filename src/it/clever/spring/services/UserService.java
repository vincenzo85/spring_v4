/**
 * UserService.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 lug 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.services;

import java.util.List;

import it.clever.spring.entities.Utente;
import it.clever.spring.exception.AuthenticationException;

/**
 * @author robgion
 *
 */
public interface UserService {

	public Utente authorize(String username, String password) throws AuthenticationException;
	
	public List<Utente> findAllUsers() throws Exception;
	
	public Utente findByPrimaryKey(Long id) throws Exception;
}
