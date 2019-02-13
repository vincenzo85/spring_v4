/**
 * LogExecutionTime.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 nov 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author robgion
 * @Target(ElementType.METHOD): Annotazione che può essere apposta solamente su metodi
 * @Retention(RetentionPolicy.RUNTIME): disponibile nella JVM a runtime
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

}
