package fr.soat.socialnetwork.ui;

import fr.soat.socialnetwork.bo.IUser;

public interface IRememberMeService {

	void rememberMe(IUser realUser);
	IUser getRememberedUser();
}
