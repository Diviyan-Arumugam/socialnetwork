package fr.soat.socialnetwork.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.google.inject.Inject;

import fr.soat.socialnetwork.service.ILoginService;

@ManagedBean(name="login")
@RequestScoped
public class LoginBean {

	private final ILoginService loginService;

	@Inject
	public LoginBean(ILoginService loginService)
	{
		this.loginService = loginService;
	}
	
	private String user;
	private String password;

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
