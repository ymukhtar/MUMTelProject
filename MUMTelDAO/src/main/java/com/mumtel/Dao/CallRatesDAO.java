package com.mumtel.Dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.ICallDetailsDAO;
import com.mumtel.Idao.ICallRatesDAO;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.CallRates;
import com.mumtel.model.Country;
import com.mumtel.model.ServiceCountry;
import com.mumtel.model.Users;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class CallRatesDAO extends GenericHibernateDAO<CallRates, Integer> implements ICallRatesDAO{

	public long getPagedCallRatesListCount(String criteriaString) {
		// TODO Auto-generated method stub
		String q="select count(c.id) From CallRates c where 1=1 ";
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

	public List<CallRates> getPagedCallRatesList(int start, int fetchSize,
			String criteriaString) {
		// TODO Auto-generated method stub
		String q="From CallRates c where 1=1";
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
	
	public void createAll(Collection<CallRates> entityList,ServiceCountry sc){
		//UPDATE All OLD CALL RATES
		for(CallRates cr:entityList){
			cr.setServiceCountry(sc);
			create(cr);
		}
	}

	public void updateOldCallRates(Date date){
		Query query=sessionFactory.getCurrentSession().createQuery("UPDATE CallRates  SET  dateTo=:dateTo");
		query.setDate("dateTo", date);
		query.executeUpdate();
	}
	public List<CallRates> getAllcallRates(int countryCode, int serviceCode,int month,int year) {
		
		StringBuilder query=new StringBuilder("SELECT cr FROM CallRates cr,ServiceCountry sc ")
							.append(" WHERE cr.serviceCountry.serviceCountryID=sc.serviceCountryID ")
							.append("and month(cr.dateFrom)=:month and year(cr.dateFrom)=:year")
							.append(" and sc.service.serviceCode=:serviceCode AND sc.country.callingCode=:countryCode and cr.dateTo IS NULL ");
		System.out.println(query.toString());
		
		Query queryH=sessionFactory.getCurrentSession().createQuery(query.toString());
		queryH.setInteger("serviceCode", serviceCode);
		queryH.setInteger("countryCode", countryCode);
		queryH.setInteger("month", month);
		queryH.setInteger("year", year);
		return queryH.list();
	}

}
