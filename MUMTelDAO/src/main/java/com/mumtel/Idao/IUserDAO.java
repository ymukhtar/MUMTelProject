package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Users;


public interface IUserDAO {
	public void create(Users user);
	public void update(Users user);
	public void delete(Users user);
	public Users get(String userName);
	public List<Users> getAll();
}
