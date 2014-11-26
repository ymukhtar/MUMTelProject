package com.mumtel.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.IUserDAO;
import com.mumtel.model.Users;

/**
 * 
 * @author atariq
 *
 */

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class UserDAO extends AbstractMumTelDAO implements IUserDAO{
	
	
	public void create(Users user) {
		sessionFactory.getCurrentSession().persist(user);
	}
	public void update(Users user) {
		sessionFactory.getCurrentSession().update(user);
	}
	public void delete(Users user) {
		sessionFactory.getCurrentSession().delete(user);
	}
	public Users get(String userName) {
		return (Users)sessionFactory.getCurrentSession().get(Users.class, userName);
	}
	@SuppressWarnings("unchecked")
	public List<Users> getAll() {
		return sessionFactory.getCurrentSession().createQuery("From user").list();
	}
}
