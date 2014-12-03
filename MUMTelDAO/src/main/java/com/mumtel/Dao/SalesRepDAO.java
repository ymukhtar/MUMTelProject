package com.mumtel.Dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.ICustomerDAO;
import com.mumtel.Idao.ISalesRepDAO;
import com.mumtel.Idao.IUserDAO;
import com.mumtel.model.Country;
import com.mumtel.model.Customer;
import com.mumtel.model.CustomerBillReport;
import com.mumtel.model.SalesRep;
import com.mumtel.model.SalesRepCommisionReport;
import com.mumtel.model.Users;

/**
 * 
 * @author atariq
 *
 */

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class SalesRepDAO extends GenericHibernateDAO<SalesRep, Long> implements ISalesRepDAO{

	public long getPagedSalesRepListCount(String searchCriteria) {
		// TODO Auto-generated method stub
		String q="select count(c.id) From SalesRep c where 1=1 ";
		if(searchCriteria!=null && searchCriteria.length()>0)
		{
			q+=" AND c.firstName like :fname or c.lastName like :lname or c.businesssPhone like :phone";
		}
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		if(searchCriteria!=null && searchCriteria.length()>0){
			query.setParameter("fname", "%"+searchCriteria+"%");
			query.setParameter("lname", "%"+searchCriteria+"%");
			query.setParameter("phone", "%"+searchCriteria+"%");
		}
		return (Long) query.uniqueResult();
	}

	public List<SalesRep> getPagedSalesRepList(int start, int fetchSize,
			String criteriaString) {
		// TODO Auto-generated method stub
		String q="From SalesRep c where 1=1";
		if(criteriaString!=null && criteriaString.length()>0)
		{
			q+=" AND c.firstName like :fname or c.lastName like :lname or c.businesssPhone like :phone";
		}
		Query query=sessionFactory.getCurrentSession().createQuery(q);
		
		if(criteriaString!=null && criteriaString.length()>0){
			query.setParameter("fname", "%"+criteriaString+"%");
			query.setParameter("lname", "%"+criteriaString+"%");
			query.setParameter("phone", "%"+criteriaString+"%");
		}
		
		query.setFirstResult(start);
		query.setMaxResults(fetchSize);
		return query.list();
	}

	public List<SalesRepCommisionReport> getCommissionDetail(String id,String month,String year) {
		// TODO Auto-generated method stub
		Query query=sessionFactory.getCurrentSession().createSQLQuery(
				"exec generate_commission :salesRep, :month, :year ")
				.addEntity(SalesRepCommisionReport.class)
				.setParameter("salesRep", id)
				.setParameter("month", month)
				.setParameter("year", year);
		return (List<SalesRepCommisionReport>)query.list();
	}
	
}
