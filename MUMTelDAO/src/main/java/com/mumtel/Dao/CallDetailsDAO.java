package com.mumtel.Dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.ICallDetailsDAO;
import com.mumtel.Idao.ICountryDAO;
import com.mumtel.model.CallDetail;
import com.mumtel.model.Country;
import com.mumtel.model.Users;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class CallDetailsDAO extends AbstractMumTelDAO implements ICallDetailsDAO{

	public void create(CallDetail callDetail) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(callDetail);
	}
	
	public void createAll(List<CallDetail> callDetails) {
		// TODO Auto-generated method stub
		for(CallDetail c:callDetails)
			sessionFactory.getCurrentSession().persist(c);
	}

	public void update(CallDetail callDetail) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(callDetail);
	}

	public void delete(CallDetail callDetail) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(callDetail);
	}

	public CallDetail get(int code) {
		// TODO Auto-generated method stub
		return (CallDetail)sessionFactory.getCurrentSession().load(Country.class, code);
	}

	public List<CallDetail> getAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("From CallDetail").list();
	}

	public long getPagedCallDetailListCount(String criteriaString) {
		// TODO Auto-generated method stub
		String q="select count(c.id) From CallDetail c where 1=1 ";
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

	public List<CallDetail> getPagedCallDetailList(int start, int fetchSize,
			String criteriaString) {
		// TODO Auto-generated method stub
		String q="From CallDetail c where 1=1";
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
