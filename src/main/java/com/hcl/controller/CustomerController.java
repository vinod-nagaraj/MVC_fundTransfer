package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.LoginDto;
import com.hcl.dto.RegistrationDto;
import com.hcl.dto.ResponseDto;
import com.hcl.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	CustomerService customerService;


	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> register(RegistrationDto registrationDto) {
		
		return new ResponseEntity<ResponseDto>(customerService.register(registrationDto), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseDto> login(LoginDto loginDto) {
		return new ResponseEntity<ResponseDto>(customerService.login(loginDto), HttpStatus.CREATED);
	}

}
