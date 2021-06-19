package com.stech_vent.customer_and_branch_crud.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class ResourceNotModifiedException extends Exception{

	private static final long serialVersionUID = 1L;

	public ResourceNotModifiedException(String message){
    	super(message);
    }
}
