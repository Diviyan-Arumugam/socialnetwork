package fr.soat.socialnetwork.service;

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

	@Inject UserDAOService userDaoService;
	
	public IUser getUser(String email, String password) {
		IUser user = userDaoService.getIUser(email, password);
		
		if (user !=null && (email.equals(user.getEmail())) &&
			(password.equals(user.getPassword())))
			return user;
		else
			return new WrongUser();
	}

}
