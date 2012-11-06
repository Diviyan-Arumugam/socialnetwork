package fr.soat.socialnetwork.dao;

import com.google.inject.persist.Transactional;

import fr.soat.socialnetwork.dao.entity.User;

public interface IUserDAO {

	@Transactional
	public abstract User find(long id);

	@Transactional
	public abstract void save(User entity);

	public abstract User getByEmail(String email);
	
}