/**
 * AuthenticationException.java
 *
 * robgion
 * www.2clever.it
 * 
 * 05 nov 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.exception;

/**
 * @author robgion
 *
 */
public class DummyException extends Exception {

	private static final long serialVersionUID = 4501526188764682166L;

	public DummyException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DummyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DummyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DummyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DummyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
