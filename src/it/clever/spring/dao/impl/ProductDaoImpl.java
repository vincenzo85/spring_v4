/**
 * ProductDaoImpl.java
 *
 * robgion
 * www.2clever.it
 * 
 * 06 giu 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.clever.spring.dao.ProductDao;
import it.clever.spring.entities.Prodotto;
import it.clever.spring.utils.CustomQueryFilter;

/**
 * @author robgion
 *
 */
@Repository(value = "productDao")
public class ProductDaoImpl extends GenericDaoImpl<Prodotto> implements ProductDao {

	/**
	 * Filtra i prodotti in base al codice
	 * 
	 * @param code
	 * @return
	 */
	public List<Prodotto> listByFilters(String code) {
		
		List<Prodotto> retList = null;
		
		CustomQueryFilter cqf = new CustomQueryFilter();
		cqf.setFieldName("codice");
		cqf.setFieldValue(code);
		
		retList = findAllByFilters(cqf);
		
		return retList;
	}
}
