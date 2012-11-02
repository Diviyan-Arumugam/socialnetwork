package fr.soat.socialnetwork.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.google.inject.Inject;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.service.ILoginService;

@ManagedBean(name="login")
@RequestScoped
public class LoginBean {

	private final ILoginService loginService;

	private String username;
	private String password;

	@Inject
	public LoginBean(ILoginService loginService)
	{
		this.loginService = loginService;
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

	public boolean validateUser() {
		IUser user = loginService.getUser(username, password);
		return user.isValidUser();		
	}
}
