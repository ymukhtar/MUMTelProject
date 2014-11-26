package com.mumtel.IService;

import java.util.List;

import com.mumtel.model.Authorities;
import com.mumtel.model.Users;
/**
 * 
 * @author Awais Tariq
 *
 */

public interface IUserService {
	
	public void createUser(Users user);
	public void updateUser(Users user);
	public void deleteUser(Users user);
	public Users getUser(String userName);
	public List<Users> getAllUser();
	
	public void createAuthorities(Authorities authorities);
	public void updateUserAuthorities(Authorities authorities);
	public void deleteUserAuthorities(Authorities authorities);
	public Authorities getAuthorities(long id);
	public List<Authorities> getAllAuthorities();
}
