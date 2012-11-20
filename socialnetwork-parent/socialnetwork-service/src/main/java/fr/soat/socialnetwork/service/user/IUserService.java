package fr.soat.socialnetwork.service.user;

import fr.soat.socialnetwork.bo.IUser;

public interface IUserService {

	public abstract IUser getIUser(String email, String password);

	/**
	 * 
	 */
	public abstract IUser findAll();

	public abstract void resetPassword(String email);

}