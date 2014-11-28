package com.mumtel.Dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mumtel.Idao.IGenericDAO;
import com.mumtel.model.Country;

@Repository
public abstract class GenericHibernateDAO<T, ID extends Serializable>
		implements IGenericDAO<T, ID> {
	
//	private static final Logger logger = LoggerFactory.getLogger(GenericHibernateDAO.class);

	@Autowired
	protected SessionFactory sessionFactory;

	private String entityName;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		entityName = persistentClass.getName();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public T get(ID id) {
		T t = (T) sessionFactory.getCurrentSession().get(getPersistentClass(),
				id);
		return t;
	}

	public List<T> getAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) sessionFactory.getCurrentSession().load(
					getPersistentClass(), id, LockOptions.UPGRADE);
		else
			entity = (T) sessionFactory.getCurrentSession().load(
					getPersistentClass(), id);

		return entity;
	}

	public void create(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
//		if (logger.isDebugEnabled()) {
//			logger.debug(entityName + " saved successfully," + entityName
//					+ " Details=" + entity);
//		}
		sessionFactory.getCurrentSession().flush();

	}

	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		T t = (T) sessionFactory.getCurrentSession().merge(entity);
		return t;

	}

	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);

	}

	@SuppressWarnings("unchecked")
	public void deleteById(ID id) {
		T entity = (T) sessionFactory.getCurrentSession().load(
				getPersistentClass(), id);
		delete(entity);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void createAll(List<T> entity) {
		// TODO Auto-generated method stub
		for(T c:entity)
			sessionFactory.getCurrentSession().persist(c);
	}
	
	public void createAll(Set<T> entity) {
		// TODO Auto-generated method stub
		for(T c:entity)
			sessionFactory.getCurrentSession().persist(c);
	}

}
