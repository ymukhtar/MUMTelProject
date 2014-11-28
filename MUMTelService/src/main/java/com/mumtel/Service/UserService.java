package com.mumtel.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mumtel.IService.IUserService;
import com.mumtel.Idao.IAuthoritiesDAO;
import com.mumtel.Idao.IUserDAO;
import com.mumtel.model.Authorities;
import com.mumtel.model.Users;


/**
 * @author awais-tariq
 * @author ymukhtar
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class UserService implements IUserService{

	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IAuthoritiesDAO authoritiesDAO;
	

	public void createUser(Users user) {
		
		userDAO.create(user);
	}
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		userDAO.update(user);
	}
	public void deleteUser(Users user) {
		// TODO Auto-generated method stub
		userDAO.delete(user);
	}
	public Users getUser(String userName) {
		
		return userDAO.get(userName);
	}
	public List<Users> getAllUser() {
		
		return userDAO.getAll();
	}
	public void createAuthorities(Authorities authorities) {
		
		authoritiesDAO.create(authorities);
	}
	public void updateUserAuthorities(Authorities authorities) {
		// TODO Auto-generated method stub
		authoritiesDAO.update(authorities);
	}
	public void deleteUserAuthorities(Authorities authorities) {
		// TODO Auto-generated method stub
		authoritiesDAO.delete(authorities);
	}
	public Authorities getAuthorities(int id) {
		// TODO Auto-generated method stub
		return authoritiesDAO.get(id);
	}
	public List<Authorities> getAllAuthorities() {
		// TODO Auto-generated method stub
		return authoritiesDAO.getAll();
	}
}
