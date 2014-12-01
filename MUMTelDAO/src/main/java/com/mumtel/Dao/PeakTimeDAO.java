package com.mumtel.Dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.IPeakTimeDAO;
import com.mumtel.model.PeakTimes;
@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class PeakTimeDAO extends GenericHibernateDAO<PeakTimes, Integer> implements IPeakTimeDAO{

	public PeakTimes getLatestPeakTime(int serviceCode, int countryCode) {
		Query query=sessionFactory.getCurrentSession().createQuery("FROM PeakTime p p.country.callingCode=:countryCode AND p.service.serviceCode=:serviceCode");
		return (PeakTimes) query.uniqueResult();
	}

}
