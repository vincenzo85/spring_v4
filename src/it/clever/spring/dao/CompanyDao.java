/**
 * CompanyDao.java
 *
 * robgion
 * www.2clever.it
 * 
 * 30 ott 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.dao;

import it.clever.spring.entities.Company;

/**
 * @author robgion
 *
 */
public interface CompanyDao extends GenericDao<Company> {

	/**
	 * Verifica ottimizzazione del caricamento con LAZY LOADING.
	 * @param companyId
	 * @return
	 */
	Company findCompanyByIdEager(Long companyId);

	/**
	 * Verifica ottimizzazione del caricamento con LAZY LOADING.
	 * @param companyId
	 * @return
	 */
	Company findCompanyByIdWithQueryEager(Long companyId);

	/**
	 * Verifica ottimizzazione del caricamento con LAZY LOADING.
	 * @param companyId
	 * @return
	 */
	Company findCompanyByIdWithNamedGraphEager(Long companyId);

	/**
	 * Verifica ottimizzazione del caricamento con LAZY LOADING.
	 * @param companyId
	 * @return
	 */
	Company findCompanyByIdWithDynamicGraphEager(Long companyId);

}