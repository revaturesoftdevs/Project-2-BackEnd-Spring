package com.group5devs.project2.exceptions;

public class NoUserFoundException extends Exception{
int bookId;
	
	
	@Override
	public String getMessage() {
		return "No user Found!";
	}
}
