/**
 * OrderDao.java
 *
 * robgion
 * www.2clever.it
 * 
 * 30 ott 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.dao;

import java.util.List;

import it.clever.spring.entities.Order;
import it.clever.spring.entities.OrderComponent;

/**
 * @author robgion
 *
 */
public interface OrderDao extends GenericDao<Order> {

	Order findOrderById(Long idOrder);

	Order findOrderByIdEager(Long idOrder);

	List<Order> findOrdersByQuantityAndPiva(int quantity, String piva, double amount);

	List<Object[]> findOrdersByQuantityAndCustomerScalar(int quantity, String piva);

	List<OrderComponent> findOrdersByQuantityAndCustomerComponent(int quantity, String piva);

}