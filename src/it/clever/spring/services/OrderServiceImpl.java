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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.clever.spring.aop.LogExecutionTime;
import it.clever.spring.dao.CustomerDao;
import it.clever.spring.dao.OrderDao;
import it.clever.spring.dao.ProductDao;
import it.clever.spring.entities.Customer;
import it.clever.spring.entities.Order;
import it.clever.spring.entities.Prodotto;
import it.clever.spring.exception.DummyException;
import it.clever.spring.utils.RequiredTx;

/**
 * @author robgion
 *
 */
@Service(value = "orderService")
//@Transactional(propagation=Propagation.MANDATORY)
//@Transactional(propagation=Propagation.REQUIRES_NEW)
//@Transactional(propagation=Propagation.NEVER)
@Transactional(propagation=Propagation.REQUIRED)
@RequiredTx
public class OrderServiceImpl implements OrderService {

	@Autowired
	@Qualifier("orderDao")
	OrderDao orderDao;

	@Autowired
	@Qualifier("customerDao")
	CustomerDao customerDao;

	@Autowired
	@Qualifier("productDao")
	ProductDao productDao;

	/**
	 * Metodo per l'inserimeto di un ordine tramite la gestione delle trasazioni.
	 * @Transactional deve essere apposto <b>solo su metodi public<b>. 
	 */
	@Override
	//@RequiredTx
	@Transactional(propagation=Propagation.REQUIRED, noRollbackFor=DummyException.class)
	public Order createNewOrder(Long customerId, Long productId, int quantity, double amount) throws Exception {

		Order retOrder = new Order();
		try {

			// Recupero del cilente
			Customer customer = customerDao.find(customerId);
			retOrder.setCliente(customer);

			// Recupero del prodotto
			Prodotto product = productDao.find(productId);
			retOrder.setProdotti1(product);

			retOrder.setQuantita(quantity);

			BigDecimal bd = new BigDecimal(amount);
			retOrder.setAmmontare(bd);

			retOrder.setDataOrdine(new Date());

			// Salvataggio dell'ordine.
			orderDao.create(retOrder);

			// Aggiornamento del numero di ordini effettuati dal cliente.
			customer.setNumOrders(1 + customer.getNumOrders());

			// ATTENZIONE!!! Settaggio di un campo not nullable in modo da
			// scatenare una eccezione.
			// customer.setRagSoc(null);
			
//			customerDao = null;
			
			try {
				
				customerDao.update(customer);
			} catch (Exception e) {
				// TODO: handle exception
				throw new DummyException("Numero ordini non aggiornato ma ordine inserito.");
			}

			
		} catch (Exception ex) {

			// Permette di forzare il meccanismo di ROLLBACK.
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			if(true)
			throw ex;
		}

		return retOrder;
	}

	@LogExecutionTime
	@Override 
	@Transactional(propagation=Propagation.NEVER)
	public List<Order> loadAllOrders() {
		return orderDao.findAll();
	}
}
