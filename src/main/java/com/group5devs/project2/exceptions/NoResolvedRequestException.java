package com.group5devs.project2.exceptions;

public class NoResolvedRequestException extends Exception {

	@Override
	public String getMessage() {
		return "There is no resolved request !!!";
	}
}
