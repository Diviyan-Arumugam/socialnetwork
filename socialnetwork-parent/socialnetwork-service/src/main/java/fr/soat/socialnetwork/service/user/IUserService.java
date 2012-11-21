package fr.soat.socialnetwork.service.user;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.service.ServiceException;

public interface IUserService {

	public abstract IUser getIUser(String email, String password);

	/**
	 * 
	 */
	public abstract IUser findAll();

	public abstract void resetPassword(String email);
	
	public IUser getByEmailPassword(String login, String password) throws ServiceException;

}