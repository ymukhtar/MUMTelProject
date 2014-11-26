package com.mumtel.Idao;

import java.util.List;

import com.mumtel.model.Authorities;


public interface IAuthoritiesDAO {
	public void create(Authorities authorities);
	public void update(Authorities authorities);
	public void delete(Authorities authorities);
	public Authorities get(long id);
	public List<Authorities> getAll();
}
