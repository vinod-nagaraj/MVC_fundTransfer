package com.hcl.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dto.LoginDto;
import com.hcl.dto.RegistrationDto;
import com.hcl.dto.ResponseDto;
import com.hcl.entity.Customers;
import com.hcl.util.FundUtil;
import com.hcl.util.Validation;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

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
		public ResponseDto register(RegistrationDto registrationDto) {
		logger.info("Inside register of CustomerDaoImpl");

		Customers customers = new Customers();
		int accNum = Validation.generatePin();
		ResponseDto responseDto = new ResponseDto();
		if (!registrationDto.equals(null)) {
			customers.setBalance(1000);
			customers.setAccnum(accNum);
			BeanUtils.copyProperties(registrationDto, customers);
			getSession().save(customers);

			responseDto.setMessage(FundUtil.REGISTERED_SUCCESS);
			responseDto.setStatusCode(HttpStatus.OK.value());
			return responseDto;
	}
		{
			logger.error("Inside register of CustomerDaoImpl's error message");
			responseDto.setMessage(FundUtil.REGISTERED_UN_SUCCESS);
			responseDto.setStatusCode(HttpStatus.OK.value());
			return responseDto;
		}
	}
	@Transactional
	public ResponseDto login(LoginDto loginDto) {
		logger.info("Inside login of CustomerDaoImpl");
		
		@SuppressWarnings("unchecked")
		List<Customers> list = getSession().createCriteria(Customers.class).list();
		ResponseDto responseDto = new ResponseDto();
		for (Customers cust : list) {
			if (loginDto.getcName().equalsIgnoreCase(cust.getcName()))
				(cust.getPassword()).equalsIgnoreCase(loginDto.getPassword());
			{

				responseDto.setMessage(FundUtil.LOGIN_SUCCESS);
				responseDto.setStatusCode(HttpStatus.ACCEPTED.value());
				return responseDto;
			}
		}
		logger.error("Inside login of CustomerDaoImpl's error message");
		responseDto.setMessage(FundUtil.LOGIN_UN_SUCCESS);
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return responseDto;
	}

		@Transactional
		public Customers findbyAccountNumber(double accnum) {
		Criteria crit = getSession().createCriteria(Customers.class);
		crit.add(Restrictions.eq("accnum", accnum));
		@SuppressWarnings("unchecked")
		List<Customers> customer = crit.list();
		return customer.get(0);
	}
}
