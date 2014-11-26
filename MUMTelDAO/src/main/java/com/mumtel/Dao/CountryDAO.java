package com.mumtel.Dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.ICountryDAO;
import com.mumtel.model.Country;
import com.mumtel.model.Users;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class CountryDAO extends AbstractMumTelDAO implements ICountryDAO{

	public void create(Country country) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(country);
	}
	
	public void createAll(List<Country> country) {
		// TODO Auto-generated method stub
		for(Country c:country)
			sessionFactory.getCurrentSession().persist(c);
	}

	public void update(Country country) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(country);
	}

	public void delete(Country country) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(country);
	}

	public Country get(int code) {
		// TODO Auto-generated method stub
		return (Country)sessionFactory.getCurrentSession().load(Country.class, code);
	}

	public List<Country> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("From Country").list();
	}

	public long getPagedCountryListCount(String criteriaString) {
		// TODO Auto-generated method stub
		String q="select count(v.id) From Country c where 1=1 ";
		if(criteriaString!=null && criteriaString.length()>0)
		{
			q+=" AND c.countryName like :countryN";
		}
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		if(criteriaString!=null && criteriaString.length()>0){
			query.setParameter("countryN", "%"+criteriaString+"%");
		}
		return (Long) query.uniqueResult();
	}

	public List<Country> getPagedCountryList(int start, int fetchSize,
			String criteriaString) {
		// TODO Auto-generated method stub
		String q="From Country c where 1=1";
		if(criteriaString!=null && criteriaString.length()>0)
		{
			q+=" AND c.employer.name like :countryN";
		}
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		
		if(criteriaString!=null && criteriaString.length()>0){
			query.setParameter("countryN", "%"+criteriaString+"%");
		}
		
		query.setFirstResult(start);
		query.setMaxResults(fetchSize);
		return query.list();
	}

}
