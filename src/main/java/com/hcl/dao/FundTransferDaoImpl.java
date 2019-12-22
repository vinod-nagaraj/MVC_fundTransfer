package com.hcl.dao;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dto.FundTransferDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.Customers;
import com.hcl.entity.Transactions;
import com.hcl.exception.LowBalanceException;
import com.hcl.util.FundUtil;

@Repository
public class FundTransferDaoImpl implements FundTransferDao {
	private static final Logger logger = LoggerFactory.getLogger(FundTransferDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	CustomerDao customerDao;

	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		return session;
	}
	@Transactional
	public ResponseDto transfer(FundTransferDto fundTransferDto) throws LowBalanceException {
		
		logger.info("Inside transfer of FundTransferDaoImpl");
		
		LocalDate localDate = LocalDate.now();
	
		Customers fromAccount = customerDao.findbyAccountNumber(fundTransferDto.getFromAcc());
		Customers toAccount = customerDao.findbyAccountNumber(fundTransferDto.getToAcc());
		
		ResponseDto responseDto = new ResponseDto();
		
		if(fromAccount.getBalance()>fundTransferDto.getAmount()) {
			fromAccount.setBalance(fromAccount.getBalance()-fundTransferDto.getAmount());
			toAccount.setBalance(toAccount.getBalance()+fundTransferDto.getAmount());
			Transactions transactions = new Transactions();
			BeanUtils.copyProperties(fundTransferDto, transactions);
			
			transactions.setTime(localDate);
			getSession().save(transactions);
			getSession().save(toAccount);
			getSession().save(fromAccount);
		
			responseDto.setMessage(FundUtil.TRANSACTION_SUCCESS);
			responseDto.setStatusCode(HttpStatus.ACCEPTED.value());
			return responseDto;
			
		} 
		throw new LowBalanceException(FundUtil.INSUFFICIENT_BALANCE);
		
	}
	
}		