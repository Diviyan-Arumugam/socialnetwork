package fr.soat.socialnetwork.ui;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.google.common.base.Optional;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.service.login.ILoginService;

@Named("login")
@RequestScoped
public class LoginBean {

	public static final String AUTH_KEY = "loggedUser";
	
	private ILoginService loginService;
   	private IRememberMeService rememberMeService;

   	private Optional<IRememberedUser> rememberedUser;

	private String login;
	private String password;
	private boolean rememberMe;

	@Inject
	private SessionBean sessionBean;

	
	protected LoginBean()
	{
	}

	@Inject
	public LoginBean(
			ILoginService loginService,
			IRememberMeService rememberMeService)
	{
		this.loginService = loginService;
		this.rememberMeService = rememberMeService;
	}

	@PostConstruct
	public void init()
	{
		fillBeanWithRememberedUser();
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public Boolean validateUser() {
//		IUser user = loginService.getUser(login, password);
		User user = new User();
		user.setLogin("dmaurer");
		user.setFirstName("Didier");
		user.setLastName("M");
		user.setPassword("pass");
		boolean validUser = user.isValidUser();
		
		if (validUser)
		{
			validateKnownUser(user);
		}
		else
		{
			addErrorMessage();
		}
		return validUser;
	}

	private void validateKnownUser(IUser user) {
		putUserInSession(user);
//		if (rememberMe)
//		{
//			rememberUser(user);
//		}
//		else
//		{
//			forgetUserIfNeeded(user);
//		}
	}

	private void forgetUserIfNeeded(IUser user) {
//		if (rememberedUser.isPresent())
//		{
//			rememberMeService.forgetMe(user);
//		}
	}

	private void rememberUser(IUser user) {
//		try {
//			rememberMeService.rememberMe(user);
//		} catch (EncryptionServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void addErrorMessage() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "wrong user", "Le login et/ou le password est incorrect !");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private void putUserInSession(IUser user) {
		sessionBean.setUser(user); 
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
		        AUTH_KEY, user);
	}

	private void fillBeanWithRememberedUser()
	{
//		try {
//			rememberedUser = rememberMeService.getRememberedUser();
//		} catch (EncryptionServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			rememberedUser = Optional.absent();
//		}
//		if (rememberedUser.isPresent())
//		{
//			fillBean(rememberedUser.get());
//			setRememberMe(true);
//		}
	}

	private void fillBean(IRememberedUser rememberedUser) {
		setLogin(rememberedUser.getLogin());
		setPassword(rememberedUser.getPassword());
	}

	public void logout() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.invalidate();
		try {
			externalContext.redirect("login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isLoggedIn() {
	    return FacesContext.getCurrentInstance().getExternalContext()
		        .getSessionMap().get(AUTH_KEY) != null;
	}

}
