package com.group5devs.project2.exceptions;

public class NoRequestException extends Exception {

	@Override
	public String getMessage() {
		return "This employee did not request for any Reimbursment yet !!";
	}
	
}
