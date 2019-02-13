/**
 * UserServiceImpl.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 lug 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import it.clever.spring.config.ConfigurationBean;
import it.clever.spring.config.WSData;
import it.clever.spring.dao.UserDao;
import it.clever.spring.dao.impl.UserDaoImpl;
import it.clever.spring.entities.Utente;
import it.clever.spring.exception.AuthenticationException;

/**
 * @author robgion
 *
 */
@Service(value="userService")
public class UserServiceImpl implements UserService {

	
	@Autowired
	@Qualifier("puntamentiWsCDM")
	WSData wsDAtaCdm;
	
	@Autowired
	@Qualifier("puntamentiWsSOS")
	WSData wsDAtaSos;
	
	
	@Autowired
	@Qualifier("jpaUserDao")
	UserDao userdao;

	
	@Override
	public Utente authorize(String username, String password) throws AuthenticationException {
		
		 List<Utente> users = userdao.findByUsernameAndPassword(username, password);
		 if(users != null && !users.isEmpty()) {
			 
			 if(users.size() > 1)
				 throw new AuthenticationException("Too many users");
			 
			 return users.get(0);
		 }
		 throw new AuthenticationException("User not found");
	}

	public List<Utente> findAllUsers() throws Exception {
		return userdao.findAll();
	}

	@Override
	public Utente findByPrimaryKey(Long id) throws Exception {
		return userdao.find(id);
	}
}
