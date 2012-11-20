package fr.soat.socialnetwork.dao;

import java.util.List;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public interface IUserDAO {

	public abstract UserDTO find(long id);
	
	public abstract List<User> findAll();

	public abstract UserDTO save(UserDTO entity);

	public IUser findByLoginPassword(String login, String password)
			throws DAOException;
	
	public UserDTO update(UserDTO entity);
	
}