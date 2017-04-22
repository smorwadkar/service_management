/**
 * 
 */
package com.sajag.manager.exception;



/**
 * @author smorwadkar
 *
 */
public class UserManagementException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with null as its detail message.
	 */
	public UserManagementException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.
	 *
	 * @param message
	 *            the detail message.
	 */
	public UserManagementException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 */
	public UserManagementException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified cause.
	 * 
	 * @param cause
	 *            the cause
	 */
	public UserManagementException(Throwable cause) {
		super(cause);
	}

}
