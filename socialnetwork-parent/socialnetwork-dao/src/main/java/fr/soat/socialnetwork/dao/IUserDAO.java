package fr.soat.socialnetwork.dao;

import com.google.inject.persist.Transactional;

import fr.soat.socialnetwork.dao.entity.UserDTO;

public interface IUserDAO {

	@Transactional
	public abstract UserDTO find(long id);

	@Transactional
	public abstract UserDTO save(UserDTO entity);

	@Transactional
	public abstract UserDTO getByEmail(String email);
	
}