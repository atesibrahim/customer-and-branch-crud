package com.stech_vent.customer_and_branch_crud.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
}
