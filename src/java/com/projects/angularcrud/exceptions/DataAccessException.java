/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.exceptions;

/**
 *
 * @author bd101009
 */
public class DataAccessException extends RuntimeException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 3620199505147655172L;

	public DataAccessException(String msg) {
		super(msg);
	}

	public DataAccessException(String msg, Throwable t) {
		super(msg, t);
	}

}

