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
public class CustomException extends RuntimeException {

	/**
	 * Unique ID for Serialized object
	 */
	private static final long serialVersionUID = 4657491283614755649L;

	public CustomException(String msg) {
		super(msg);
	}

	public CustomException(String msg, Throwable t) {
		super(msg, t);
	}

}

