package com.hcl.dao;

import com.hcl.dto.LoginDto;
import com.hcl.dto.RegistrationDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.Customers;

public interface CustomerDao {

	public ResponseDto register(RegistrationDto registrationDto);

	public ResponseDto login(LoginDto loginDto) ;
	
	public Customers findbyAccountNumber(double accnum);

}
