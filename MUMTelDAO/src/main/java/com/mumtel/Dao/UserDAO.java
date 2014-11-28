package com.mumtel.Dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.IUserDAO;
import com.mumtel.model.Country;
import com.mumtel.model.Users;

/**
 * 
 * @author atariq
 *
 */

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class UserDAO extends GenericHibernateDAO<Users, Integer> implements IUserDAO{

	public Users get(String userName) {
		// TODO Auto-generated method stub
		Query query=sessionFactory.getCurrentSession().createQuery("FROM Users c where c.userName=:userName");
		query.setString("userName", userName);
		query.setMaxResults(1);
		return (Users)query.uniqueResult();	
	}
	
}
