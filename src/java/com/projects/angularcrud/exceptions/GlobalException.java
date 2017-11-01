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

/**
 *
 * @author bd101009
 */
public class GlobalException extends RuntimeException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 4657491283614755649L;

	public GlobalException(String msg) {
		super(msg);
	}

	public GlobalException(String msg, Throwable t) {
		super(msg, t);
	}

}



