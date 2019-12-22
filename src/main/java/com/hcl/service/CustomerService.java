package com.hcl.service;

import com.hcl.dto.LoginDto;
import com.hcl.dto.RegistrationDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.Customers;

public interface CustomerService {
	
	public ResponseDto register(RegistrationDto registrationDto);

	public ResponseDto login(LoginDto loginDto) ;
	
	public Customers findbyAccountNumber(double accnum);

}
