package com.mumtel.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.Idao.IAuthoritiesDAO;
import com.mumtel.Idao.IGenericDAO;
import com.mumtel.model.Authorities;


@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class AuthoritiesDAO extends GenericHibernateDAO<Authorities, Integer> implements IAuthoritiesDAO{

}
