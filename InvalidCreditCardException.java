/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.pio.exceptions;

/**
 *
 * @author bd101009
 */
public class InvalidCreditCardException extends RuntimeException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 6648725043534411041L;

	public InvalidCreditCardException(String msg) {
		super(msg);
	}

	public InvalidCreditCardException(String msg, Throwable t) {
		super(msg, t);
	}

}

