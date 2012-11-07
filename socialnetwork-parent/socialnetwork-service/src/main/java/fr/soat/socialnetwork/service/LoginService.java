package fr.soat.socialnetwork.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.bo.WrongUser;

@Named("LoginService")
@ApplicationScoped
@Default
public class LoginService implements ILoginService {

	public IUser getUser(String userName, String password) {
		if (("chris".equals(userName)) &&
			("chris".equals(password)))
			return new User();
		else
			return new WrongUser();
	}

}
