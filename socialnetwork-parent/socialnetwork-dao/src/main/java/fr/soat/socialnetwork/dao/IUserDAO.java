package fr.soat.socialnetwork.dao;

import java.util.List;

import fr.soat.socialnetwork.bo.IUser;

public interface IUserDAO {

	public abstract IUser find(long id);
	
	public abstract List<IUser> findAll();

	public abstract IUser save(IUser entity) throws DAOException;

	public IUser findByLoginPassword(String login, String password)
			throws DAOException;
	
	public IUser update(IUser entity);
	
}