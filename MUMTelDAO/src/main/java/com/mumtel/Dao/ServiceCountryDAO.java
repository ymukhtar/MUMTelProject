package com.mumtel.Dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.ICallDetailsDAO;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.Idao.IServiceCountryDAO;
import com.mumtel.Idao.IServiceDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Service;
import com.mumtel.model.ServiceCountry;
import com.mumtel.model.Users;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class ServiceCountryDAO extends GenericHibernateDAO<ServiceCountry, Integer> implements IServiceCountryDAO{

	public long getPagedServiceCountryListCount(String criteriaString) {
		// TODO Auto-generated method stub
		String q="select count(c.id) From ServiceCountry c where 1=1 ";
		if(criteriaString!=null && criteriaString.length()>0)
		{
			q+=" AND c.fromTel like :fromt or c.toTel like :tot";
		}
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		
		if(criteriaString!=null && criteriaString.length()>0){
			query.setParameter("fromt", "%"+criteriaString+"%");
			query.setParameter("tot", "%"+criteriaString+"%");
		}
		return (Long) query.uniqueResult();
	}

	public List<ServiceCountry> getPagedServiceCountryList(int start, int fetchSize,
			String criteriaString) {
		// TODO Auto-generated method stub
		String q="From ServiceCountry c where 1=1";
		if(criteriaString!=null && criteriaString.length()>0)
		{
			q+=" AND c.fromTel like :fromt or c.toTel like :tot";
		}
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		
		if(criteriaString!=null && criteriaString.length()>0){
			query.setParameter("fromt", "%"+criteriaString+"%");
			query.setParameter("tot", "%"+criteriaString+"%");
		}
		
		query.setFirstResult(start);
		query.setMaxResults(fetchSize);
		return query.list();
	}
}
