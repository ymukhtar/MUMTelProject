package com.mumtel.Dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.ICustomerDAO;
import com.mumtel.Idao.IUserDAO;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.Users;

/**
 * 
 * @author atariq
 *
 */

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class CustomerDAO extends GenericHibernateDAO<Customer, Integer> implements ICustomerDAO{
	
}