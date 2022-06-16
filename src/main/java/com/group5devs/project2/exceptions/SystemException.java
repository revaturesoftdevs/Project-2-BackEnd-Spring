package com.group5devs.project2.exceptions;

public class SystemException extends Exception{
	
	@Override
	public String getMessage() {
		return "There was an internal error! Please try again later!";
	}

}
