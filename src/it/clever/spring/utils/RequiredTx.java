/**
 * NotSupportedTx.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 nov 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.clever.spring.exception.DummyException;

/**
 * @author robgion
 *
 * Crea una annotation per gestire operazioni che 
 * necessitano di transazioni: 
 * 
 * 1. l'operazione supporta la 
 * 	  transazione corrente ma in caso non ve ne fosse una aperta ne crea una nuova.
 * 
 * 2. l'operazione non esegue il rollback per eccezioni del tipo DummyException.
 */

// dove va questa ? sul metodo o sulla classe ? ... (la classe sarebbe il tipo ...

@Target({ElementType.METHOD, ElementType.TYPE})

// quando viene eseguita ? ... retention .... presa in considerazione a run time 


// costruita per portarmi dietro un set di configurazioni di spring ....
@Retention(RetentionPolicy.RUNTIME)

// la esegue ogni volta che vede sul metodo @RequiredTx .... 

@Transactional(propagation=Propagation.REQUIRED, noRollbackFor=DummyException.class)
public @interface RequiredTx {

	
	
}
