package fr.soat.socialnetwork.ui;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.service.ILoginService;

@Named("login")
@RequestScoped
public class LoginBean {

	private ILoginService loginService;
	private FacesContext context;
   	private UserSessionBean userSession;

	private String username;
	private String password;
	

	protected LoginBean()
	{
	}
	
	@Inject
	public LoginBean(
			ILoginService loginService,
			FacesContext context,
			UserSessionBean userSession)
	{
		this.loginService = loginService;
		this.context = context;
		this.userSession = userSession;
	}
	
	public String getUser() {
		return username;
	}
	public void setUser(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean validateUser() {
		IUser user = loginService.getUser(username, password);
		boolean validUser = user.isValidUser();
		if (validUser)
		{
			putUserInSession(user);
		}
		else
		{
			addErrorMessage();
		}
		return validUser;
	}

	private void addErrorMessage() {
		FacesMessage message = new FacesMessage("wrong user");
		context.addMessage(null, message);
	}

	private void putUserInSession(IUser user) {
		getUserSession().setUser(user);
	}

	public UserSessionBean getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSessionBean userSession) {
		this.userSession = userSession;
	}
}
