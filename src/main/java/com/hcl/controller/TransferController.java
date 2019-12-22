package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.FundTransferDto;
import com.hcl.dto.ResponseDto;
import com.hcl.exception.LowBalanceException;
import com.hcl.service.FundTransferService;

@RestController
@RequestMapping("/api")
public class TransferController {
	
	@Autowired
	FundTransferService fundTransferService;

	@RequestMapping(value = "transfer" , method = RequestMethod.POST)
public ResponseEntity<ResponseDto> transfer(FundTransferDto fundTransferDto)throws LowBalanceException {
		
		return new ResponseEntity<ResponseDto>(fundTransferService.transfer(fundTransferDto), HttpStatus.CREATED);
	}
}
