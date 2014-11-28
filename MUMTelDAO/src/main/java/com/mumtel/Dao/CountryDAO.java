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
public class CountryDAO extends GenericHibernateDAO<Country, Integer> implements ICountryDAO{
	public Country get(String countryName) {
		Query query=sessionFactory.getCurrentSession().createQuery("FROM Country c where c.countryName=:countryName");
		query.setString("countryName", countryName);
		query.setMaxResults(1);
		return (Country)query.uniqueResult();	
	}
	public long getPagedCountryListCount(String criteriaString) {
		// TODO Auto-generated method stub
		String q="select count(c.id) From Country c where 1=1 ";
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
			q+=" AND c.countryName like :countryN";
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
