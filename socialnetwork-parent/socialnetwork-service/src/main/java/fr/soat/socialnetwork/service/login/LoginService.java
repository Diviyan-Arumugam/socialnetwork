package fr.soat.socialnetwork.service.login;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.WrongUser;
import fr.soat.socialnetwork.service.user.IUserService;

@Named("LoginService")
@ApplicationScoped
@Default
public class LoginService implements ILoginService {

	@Inject
	IUserService userService;

	public IUser getUser(String login, String password) {
		IUser user = new WrongUser();

		user = userService.getIUser(login, password);
		
		return user;
	}

}
