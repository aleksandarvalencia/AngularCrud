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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An example of an application-specific exception. Defined here for convenience
 * as we don't have a real domain model or its associated business logic.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such order")
public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8790211652911971729L;

	public OrderNotFoundException(String orderId) {
		super(orderId + " not found");
	}
}
