/**
 * LogServiceAspect.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 nov 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author robgion
 *
 */

// è un gestore di eventi...in attesa di un evento 

// quale evento??

@Aspect
@Component
// ha uno scope singleton 
@Scope(value = "singleton")
public class LogServiceAspect {

	
	// definisco i pointcut ... punti di intervento ...
	
	// in cui si deve svegliare... 
	
	// un target .... 
	
	
	// prima e dopo di una classe che implementa l'interfaccia... Loggable...
	
	
	
	
	@Pointcut("target(it.clever.spring.services.LoggableAopService)")
	public void logPointCut() {}
	// prima 
	@Before("logPointCut()")
	public void logBefore(JoinPoint joinPoint) {

		System.out.println("\n\nlogBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}
	
	// dopo... 

	@After("logPointCut()")
	public void logAfter(JoinPoint joinPoint) {

		System.out.println("\n\nlogAfter() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");

	}

	@AfterThrowing(pointcut="logPointCut()", throwing="error")
	public void throwingError(JoinPoint joinPoint, Throwable error) {

		System.out.println("\n\throwingError() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
	}
}
