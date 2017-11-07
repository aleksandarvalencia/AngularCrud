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
public class UnhandledException extends RuntimeException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 4657422283614755649L;

	public UnhandledException(String msg) {
		super(msg);
	}

	public UnhandledException(String msg, Throwable t) {
		super(msg, t);
	}

}

