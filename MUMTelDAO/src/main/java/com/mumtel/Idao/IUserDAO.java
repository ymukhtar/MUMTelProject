package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Users;


public interface IUserDAO extends IGenericDAO<Users, Integer>{
	public Users get(String userName);
}
