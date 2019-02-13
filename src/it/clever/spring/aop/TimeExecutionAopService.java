/**
 * TimeExecutionAopService.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 nov 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author robgion
 *
 */
@Aspect
@Component
public class TimeExecutionAopService {
// avvolge il metodo ... entrando in merito all'esecuzione
	// fai business intellingence.... 
	
	// ASP .... tutte le volte che vedi l'annotazione LogExecutionTIme... 
	
	// quando inizia e quando finisce mi setta ... i ms di esecuzione.... 
	
	// lo avvolgo ... 
	
	// posso pilotare o meno l'esecuzione del metodo...
	
	
	
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();
	    
	    
	    // vai ... procedi....
	    
	    Object proceed = joinPoint.proceed();
	    // .. fa il return ....
	    
	    
	    long executionTime = System.currentTimeMillis() - start;
	 
	    System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}
}
