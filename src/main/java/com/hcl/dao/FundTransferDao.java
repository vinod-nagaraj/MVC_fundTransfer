package com.hcl.dao;

import com.hcl.dto.FundTransferDto;
import com.hcl.dto.ResponseDto;
import com.hcl.exception.LowBalanceException;

public interface FundTransferDao {
	
	public ResponseDto transfer(FundTransferDto fundTransferDto)throws LowBalanceException;

}
