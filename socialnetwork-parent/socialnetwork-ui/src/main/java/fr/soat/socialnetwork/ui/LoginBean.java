package fr.soat.socialnetwork.ui;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.base.Optional;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.service.encryption.EncryptionServiceException;
import fr.soat.socialnetwork.service.login.ILoginService;

@Named("login")
@RequestScoped
public class LoginBean {

	private ILoginService loginService;
	private FacesContext context;
   	private UserSessionBean userSession;
   	private IRememberMeService rememberMeService;

	private String username;
	private String password;
	private boolean rememberMe;

	protected LoginBean()
	{
	}

	@Inject
	public LoginBean(
			ILoginService loginService,
			IRememberMeService rememberMeService,
			FacesContext context,
			UserSessionBean userSession)
	{
		this.loginService = loginService;
		this.rememberMeService = rememberMeService;
		this.context = context;
		this.userSession = userSession;
	}

	@PostConstruct
	public void init()
	{
		fillBeanWithRememberedUser();
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

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public Boolean validateUser() {
		IUser user = loginService.getUser(username, password);
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
		rememberUserIfNeeded(user);
	}

	private void rememberUserIfNeeded(IUser user) {
		if (rememberMe)
		{
			try {
				rememberMeService.rememberMe(user);
			} catch (EncryptionServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	private void fillBeanWithRememberedUser()
	{
		Optional<IRememberedUser> rememberedUser;
		try {
			rememberedUser = rememberMeService.getRememberedUser();
		} catch (EncryptionServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rememberedUser = Optional.absent();
		}
		if (rememberedUser.isPresent())
		{
			fillBean(rememberedUser.get());
			setRememberMe(true);
		}
	}

	private void fillBean(IRememberedUser rememberedUser) {
		setUser(rememberedUser.getName());
		setPassword(rememberedUser.getPassword());
	}
}
