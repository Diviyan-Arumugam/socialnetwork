package fr.soat.socialnetwork.dao;

import fr.soat.socialnetwork.dao.entity.UserDTO;

public interface IUserDAO {

//	@Transactional
	public abstract UserDTO find(long id);

//	@Transactional
	public abstract UserDTO save(UserDTO entity);

//	@Transactional
	public abstract UserDTO getByEmail(String email);
	
}