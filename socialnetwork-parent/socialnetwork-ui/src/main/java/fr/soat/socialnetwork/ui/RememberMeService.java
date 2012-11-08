package fr.soat.socialnetwork.ui;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.WrongUser;

@Named
@ApplicationScoped
@Default
public class RememberMeService implements IRememberMeService {
	
	@Inject
	public RememberMeService()
	{
		
	}
	
	@Override
	public void rememberMe(IUser realUser)
	{
	}

	@Override
	public IUser getRememberedUser() {
		return new WrongUser();
	}

}
