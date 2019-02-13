/**
 * robgion
 * www.2clever.it
 * 
 * 10 apr 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.clever.spring.utils.CustomQueryFilter;

/**
 * Interfaccia del repository generico. 
 * Tutte le altre altre interfacce dei repository devono 
 * implementare questa.
 * 
 * @author robgion
 *
 * @param <T>
 */
public interface GenericDao<T> {


    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);

	List<T> findAll();   
	
	List<T> findAllByFilters(CustomQueryFilter... filters);

	EntityManager getEntityManager();

}