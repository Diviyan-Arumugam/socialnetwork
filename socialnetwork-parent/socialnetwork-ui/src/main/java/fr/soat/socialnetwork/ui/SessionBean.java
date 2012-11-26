package fr.soat.socialnetwork.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.Discussion;
import fr.soat.socialnetwork.bo.IUser;

@Named("sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
	
	private static final long serialVersionUID = -702359602670786575L;
	
	private IUser user;

	@PostConstruct
	public void init()
	{
	}

	public void setUser(IUser user)
	{
		this.user = user;
	}
	
	public IUser getUser()
	{
		return user;
	}
}
