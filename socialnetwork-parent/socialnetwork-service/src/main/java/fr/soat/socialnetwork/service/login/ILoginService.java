package fr.soat.socialnetwork.service.login;

import fr.soat.socialnetwork.bo.IUser;

public interface ILoginService {
	IUser getUser(String userName, String password);
}