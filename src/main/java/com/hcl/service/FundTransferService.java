package com.hcl.service;

import com.hcl.dto.FundTransferDto;
import com.hcl.dto.ResponseDto;
import com.hcl.exception.LowBalanceException;

public interface FundTransferService {

	public ResponseDto transfer(FundTransferDto fundTransferDto)throws LowBalanceException;
}
