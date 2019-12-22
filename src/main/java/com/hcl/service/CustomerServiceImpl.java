package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dao.CustomerDao;
import com.hcl.dto.LoginDto;
import com.hcl.dto.RegistrationDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.Customers;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerDao customerDao;


	public ResponseDto register(RegistrationDto registrationDto) {
		
		return customerDao.register(registrationDto); 
	}

	
	public ResponseDto login(LoginDto loginDto) {
		
		return customerDao.login(loginDto);
				
	}

	
	public Customers findbyAccountNumber(double accnum) {
		return customerDao.findbyAccountNumber(accnum);
		
	}

}
