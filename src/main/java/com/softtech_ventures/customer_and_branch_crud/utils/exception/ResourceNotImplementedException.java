package com.softtech_ventures.customer_and_branch_crud.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class ResourceNotImplementedException extends Exception{

	private static final long serialVersionUID = 1L;

	public ResourceNotImplementedException(String message){
    	super(message);
    }
}
