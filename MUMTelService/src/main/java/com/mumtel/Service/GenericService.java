package com.mumtel.Service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.IGenericService;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.model.Country;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public abstract class GenericService<T, ID extends Serializable>
		implements IGenericService<T, ID> {
	

	protected IGenericDAO genericDAO;

	public abstract void setGenericDAO();

	public GenericService() {
		
	}
	
	@PostConstruct
	public void initIt() throws Exception {
		setGenericDAO();
	  System.out.println("Init method after properties are set : " );
	}

	@SuppressWarnings("unchecked")
	public T get(ID id) {
		T t = (T) genericDAO.get(id);
		return t;
	}

	public List<T> getAll() {	
		return genericDAO.getAll();
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		T entity = (T) genericDAO.findById(id, lock);
		return entity;
	}

	public void create(T entity) {
		genericDAO.create(entity);
	}

	public void update(T entity) {
		genericDAO.update(entity);
		//sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		T t = (T) genericDAO.merge(entity);
		return t;
	}

	public void delete(T entity) {
		genericDAO.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public void deleteById(ID id) {
		genericDAO.deleteById(id);
	}
	
	public void createAll(Collection<T> entity) {
		genericDAO.createAll(entity);
	}

}
