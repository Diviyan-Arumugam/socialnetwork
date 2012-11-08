package fr.soat.socialnetwork.service;

import fr.soat.socialnetwork.bo.IUser;

public interface ILoginService {
	IUser getUser(String userName, String password);
}