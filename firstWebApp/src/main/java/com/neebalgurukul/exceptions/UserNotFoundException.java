package com.neebalgurukul.exceptions;

public class UserNotFoundException extends Exception{
	
	public UserNotFoundException(){};
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "Error: User was not found...";
}
}
