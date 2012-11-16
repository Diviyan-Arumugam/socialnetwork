package fr.soat.socialnetwork.dao;

import java.util.List;

import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public interface IUserDAO {

	public abstract UserDTO find(long id);
	
	public abstract List<User> findAll();

	public abstract UserDTO save(UserDTO entity);

	public abstract UserDTO getByEmail(String email);
	
	public UserDTO update(UserDTO entity);
	
}