/**
 * UserDaoImpl.java
 *
 * robgion
 * www.2clever.it
 * 
 * 31 ott 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.clever.spring.dao.UserDao;
import it.clever.spring.entities.Utente;
import it.clever.spring.utils.CustomQueryFilter;

/**
 * @author robgion
 *
 */
@Repository(value = "jpaUserDao")
public class UserDaoImpl extends GenericDaoImpl<Utente> implements UserDao {

	@Override
	public List<Utente> findByUsernameAndPassword(String username, String password) {

		// Costruzione dei filtri
		CustomQueryFilter usernameFilter = new CustomQueryFilter();
		usernameFilter.setFieldName("username");
		usernameFilter.setFieldValue(username);

		CustomQueryFilter passwordFilter = new CustomQueryFilter();
		passwordFilter.setFieldName("password");
		passwordFilter.setFieldValue(password);

		List<Utente> users = findAllByFilters(usernameFilter, passwordFilter);

		return users;
	}

}
