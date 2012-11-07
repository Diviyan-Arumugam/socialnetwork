package fr.soat.socialnetwork.ui;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.IUser;

@Named("userSession")
@SessionScoped
public class UserSessionBean implements Serializable {
	
	private static final long serialVersionUID = -702359602670786575L;
	
	private IUser user;

	public void setUser(IUser user)
	{
		this.user = user;
	}
	
	public IUser getUser()
	{
		return user;
	}

}
