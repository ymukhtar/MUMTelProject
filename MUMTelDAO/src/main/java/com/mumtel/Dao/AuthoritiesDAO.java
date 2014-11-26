package com.mumtel.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.IAuthoritiesDAO;
import com.mumtel.model.Authorities;


@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class AuthoritiesDAO extends AbstractMumTelDAO implements IAuthoritiesDAO{
	
	public void create(Authorities authorities) {
		sessionFactory.getCurrentSession().persist(authorities);
	}
	public void update(Authorities authorities) {
		sessionFactory.getCurrentSession().update(authorities);
	}
	public void delete(Authorities authorities) {
		sessionFactory.getCurrentSession().delete(authorities);
	}
	public Authorities get(long id) {
		return (Authorities)sessionFactory.getCurrentSession().get(Authorities.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Authorities> getAll() {
		return sessionFactory.getCurrentSession().createQuery("From Authorities").list();
	}

}
