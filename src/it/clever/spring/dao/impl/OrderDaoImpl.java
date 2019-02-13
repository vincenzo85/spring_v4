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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.clever.spring.dao.OrderDao;
import it.clever.spring.entities.Order;
import it.clever.spring.entities.OrderComponent;
import it.clever.spring.utils.CustomQueryFilter;

/**
 * @author robgion
 *
 */
@Repository(value = "orderDao")
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public Order findOrderById(Long idOrder) {

		Order order = null;
		try {
			order = em.find(Order.class, idOrder);
		} finally {
			em.close();
		}
		return order;
	}
	
	/* (non-Javadoc)
	 * @see com.opengest.jpa.entities.dao.OrderDao#findOrderByIdEager(java.lang.Long)
	 */
	@Override
	public Order findOrderByIdEager(Long idOrder) {
		
		Order order = null;
		try {
			
			order = em.find(Order.class, idOrder);
			
			// Chiamate a vuoto su entity collegate.
			order.getCliente().getCodice();
			order.getProdotti1().getCodice();
			
		} finally {
			em.close();
		}
		return order;
	}

	/**
	 * Metodo per l'utilizzo di CriteriaQuery con gestione a livello di super-classe.
	 */
	@Override public List<Order> findOrdersByQuantityAndPiva(int quantity, String piva, double amount) {

		List<Order> orders = null;

		CustomQueryFilter quantityFilter = new CustomQueryFilter();
		quantityFilter.setFieldName("quantita");
		quantityFilter.setFieldValue(quantity);
		
		CustomQueryFilter amountFilter = new CustomQueryFilter();
		amountFilter.setFieldName("ammontare");
		amountFilter.setFieldValue(amount);
		
		orders = findAllByFilters(quantityFilter, amountFilter);
		
		return orders;
	}

	@Override
	public List<Object[]> findOrdersByQuantityAndCustomerScalar(int quantity, String piva) {

		
		List<Object[]> orders = null;
		try {

			// Cross join non ottimizzata
			Query q = em
					.createQuery("select o.idOrdine, o.cliente.idCliente from Order o where o.quantita = :quant and o.cliente.piva = :piva");

			// Evita la Cross join eseguendo una inner join.
//			.createQuery("select o.idOrdine, c.idCliente from Order o o.cliente c where o.quantita = :quant and c.piva = :piva");

			q.setParameter("quant", quantity);
			q.setParameter("piva", piva);

			orders = q.getResultList();

		} finally {
			em.close();
		}
		return orders;
	}
	
	@Override
	public List<OrderComponent> findOrdersByQuantityAndCustomerComponent(int quantity, String piva) {
		
		List<OrderComponent> orders = null;
		try {
			Query q = em
					.createQuery("select new com.opengest.jpa.entities.OrderComponent(o.idOrdine, c.idCliente) from Order o join o.cliente c where o.quantita = :quant and c.piva = :piva");
			
			q.setParameter("quant", quantity);
			q.setParameter("piva", piva);
			
			orders = q.getResultList();
			
		} finally {
			em.close();
		}
		return orders;
	}

	
	// transactional mi gestisce la transazione.... 
	@Transactional
	public void save(Order order) {

		try {

			em.persist(order);
			em.flush();


		} finally {
			if (em != null) {
				em.close();
			}

		}
	}
}
