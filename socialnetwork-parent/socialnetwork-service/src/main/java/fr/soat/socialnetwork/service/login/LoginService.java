package fr.soat.socialnetwork.service.login;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.WrongUser;
import fr.soat.socialnetwork.dao.mapper.UserDAOService;

@Named("LoginService")
@ApplicationScoped
@Default
public class LoginService implements ILoginService {

	@Inject
	UserDAOService userDaoService;

	public IUser getUser(String login, String password) {
		IUser user = userDaoService.getIUser(login, password);

		if (user != null) {
			return user;
		}
		return new WrongUser();
	}

}
