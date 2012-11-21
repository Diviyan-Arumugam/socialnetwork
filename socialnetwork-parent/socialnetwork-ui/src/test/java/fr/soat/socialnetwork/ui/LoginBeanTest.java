package fr.soat.socialnetwork.ui;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.WrongUser;
import fr.soat.socialnetwork.common.services.encryption.EncryptionServiceException;
import fr.soat.socialnetwork.service.login.ILoginService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginBeanTest {

	@Mock
	private ILoginService loginService;

	@Mock
	private IRememberMeService rememberMeService;

	@Mock
	private UserSessionBean userSession;

	private FacesContext facesContext = ContextMocker.mockFacesContext();

	private static final String userLogin = "login";
	private static final String userPassword = "password";

	private LoginBean loginBean;

	private void createEmptyLoginBean() {
		loginBean = new LoginBean(
				loginService,
				rememberMeService);
		loginBean.init();
	}

	private void createLoginBean()
	{
		createEmptyLoginBean();
		loginBean.setLogin(userLogin);
		loginBean.setPassword(userPassword);
	}

	private void createLoginBeanWithWrongUser() {
		createWrongUser();
		createLoginBean();
	}

	private void createWrongUser() {
		IUser wrongUser = new WrongUser();
		when
			(loginService.getUser(userLogin, userPassword)).
		thenReturn
			(wrongUser);
	}

	private IUser createLoginBeanWithValidUser() {
		IUser realUser = createValidUser();
		when
			(loginService.getUser(userLogin, userPassword)).
		thenReturn
			(realUser);

		createLoginBean();

		return realUser;
	}

	private IUser createValidUser() {
		IUser realUser = mock(IUser.class);
		when
			(realUser.isValidUser()).
		thenReturn
			(true);

		when
			(realUser.getLogin()).
		thenReturn
			(userLogin);

		when
			(realUser.getPassword()).
		thenReturn
			(userPassword);

		return realUser;
	}

	@Test
	public void shouldNotValidateWrongUser() throws EncryptionServiceException
	{
		// given
		loginBeanDoesntHaveARememberedUser();
		createLoginBeanWithWrongUser();

		// when
		boolean result = loginBean.validateUser();

		// then
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void shouldValidateRealUser() throws EncryptionServiceException
	{
		// given
		loginBeanDoesntHaveARememberedUser();
		createLoginBeanWithValidUser();

		// when
		boolean result = loginBean.validateUser();

		// then
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void shouldPutValidUserInSession() throws EncryptionServiceException
	{
		// given
		loginBeanDoesntHaveARememberedUser();
		IUser realUser = createLoginBeanWithValidUser();

		// when
		loginBean.validateUser();

		// then
		verify(userSession).setUser(realUser);
	}

	@Test
	public void shouldDisplayErrorMessageForWrongUser() throws EncryptionServiceException
	{
		// given
		loginBeanDoesntHaveARememberedUser();
		createLoginBeanWithWrongUser();

		// when
		loginBean.validateUser();

		// then
		verify(facesContext).addMessage(isNull(String.class), isNotNull(FacesMessage.class));
	}

	@Test
	public void shouldRememberValidUserWhenAskedTo() throws EncryptionServiceException
	{
		// given
		loginBeanDoesntHaveARememberedUser();
		IUser realUser = createLoginBeanWithValidUser();
		loginBean.setRememberMe(true);

		// when
		loginBean.validateUser();

		// then
		verify(rememberMeService).rememberMe(realUser);
	}

	@Test
	public void shouldNotRememberValidUserWhenNotAskedTo() throws EncryptionServiceException
	{
		// given
		loginBeanDoesntHaveARememberedUser();
		IUser realUser = createLoginBeanWithValidUser();
		loginBean.setRememberMe(false);

		// when
		loginBean.validateUser();

		// then
		verify(rememberMeService, never()).rememberMe(realUser);
	}

	@Test
	public void shouldFillBeanAndRememberMeWithRememberedUser() throws EncryptionServiceException
	{
		// given
		loginBeanHasARememberedUser();
		createEmptyLoginBean();

		// when
		loginBean.init();

		// then
		assertThat(loginBean.getLogin(), is(equalTo(userLogin)));
		assertThat(loginBean.getPassword(), is(equalTo(userPassword)));
		assertThat(loginBean.isRememberMe(), is(equalTo(true)));
	}

	private void loginBeanHasARememberedUser() throws EncryptionServiceException {
		Optional<IRememberedUser> rememberedUser = Optional.of(createValidRememberdUser());

		when
			(rememberMeService.getRememberedUser()).
		thenReturn
			(rememberedUser);
	}

	private void loginBeanDoesntHaveARememberedUser() throws EncryptionServiceException
	{
		Optional<IRememberedUser> rememberedUser = Optional.absent();

		when
			(rememberMeService.getRememberedUser()).
		thenReturn
			(rememberedUser);
	}

	private IRememberedUser createValidRememberdUser() {
		return new RememberedUser(userLogin, userPassword);
	}

	@Test
	public void shouldRemoveAnyPreviousRememberedUser() throws EncryptionServiceException
	{
		// given
		loginBeanHasARememberedUser();
		createLoginBeanWithValidUser();
		loginBean.setRememberMe(false);

		// when
		loginBean.validateUser();

		// then
		ArgumentCaptor<IUser> rememberedUser = ArgumentCaptor.forClass(IUser.class);
		verify(rememberMeService).forgetMe(rememberedUser.capture());
		assertThat(rememberedUser.getValue().getLogin(), is(equalTo(userLogin)));
	}

	@Test
	public void shouldInvalidateSessionWhenLoggingOut() throws EncryptionServiceException
	{
		// given
		ExternalContext externalContext = mock(ExternalContext.class);
		HttpSession session = mock(HttpSession.class);
		when
			(externalContext.getSession(false)).
		thenReturn
			(session);
		when
			(facesContext.getExternalContext()).
		thenReturn
			(externalContext);

		loginBeanDoesntHaveARememberedUser();
		IUser realUser = createLoginBeanWithValidUser();
		loginBean.setRememberMe(false);
		loginBean.validateUser();

		// when
		loginBean.logout();

		// then
		verify(session).invalidate();

	}
}
