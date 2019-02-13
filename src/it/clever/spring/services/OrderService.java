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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.clever.spring.entities.Order;
import it.clever.spring.utils.RequiredTx;

/**
 * @author robgion
 *
 */

// tutte le classi che estendono LoggableAopService

// cosa avviene prima e dopo viene avviato ... 

//@Before("logPointCut()")
//public void logBefore(JoinPoint joinPoint) {
//
//	System.out.println("\n\nlogBefore() is running!");
//	System.out.println("hijacked : " + joinPoint.getSignature().getName());
//	System.out.println("******");
//}
//
//// dopo... 
//
//@After("logPointCut()")
//public void logAfter(JoinPoint joinPoint) {
//
//	System.out.println("\n\nlogAfter() is running!");
//	System.out.println("hijacked : " + joinPoint.getSignature().getName());
//	System.out.println("******");
//
//}
// del file logserviceAspect... 
public interface OrderService extends LoggableAopService {

	public Order createNewOrder(Long customerId, Long productId, int quantity, double amount) throws Exception;

	public List<Order> loadAllOrders() throws Exception;
}
