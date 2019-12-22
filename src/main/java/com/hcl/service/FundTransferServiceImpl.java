package com.hcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dao.FundTransferDao;
import com.hcl.dto.FundTransferDto;
import com.hcl.dto.ResponseDto;
import com.hcl.exception.LowBalanceException;

@Service
public class FundTransferServiceImpl implements FundTransferService {
	@Autowired
	FundTransferDao fundTransferDao;


	public ResponseDto transfer(FundTransferDto fundTransferDto)throws LowBalanceException {
		
		return fundTransferDao.transfer(fundTransferDto);
				
	}

}
