package com.hcl.exception;

public class LowBalanceException extends Exception {
	private static final long serialVersionUID = 1L;

	public LowBalanceException(String message)
	{
		super(message);  
	}


}
