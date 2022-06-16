package com.group5devs.project2.exceptions;

public class EmptyStoreException extends Exception{
	
	@Override
	public String getMessage() {
		return "Store is Empty!";
	}

}
