package fr.soat.socialnetwork.ui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.WrongUser;
import fr.soat.socialnetwork.service.encryption.EncryptionServiceException;
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
				rememberMeService,
				facesContext,
				userSession);
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
	public void shouldNotValidateWrongUser()
	{
		// given
		createLoginBeanWithWrongUser();

		// when
		boolean result = loginBean.validateUser();

		// then
		assertThat(result, is(equalTo(false)));
	}

	@Test
	public void shouldValidateRealUser()
	{
		// given
		createLoginBeanWithValidUser();

		// when
		boolean result = loginBean.validateUser();

		// then
		assertThat(result, is(equalTo(true)));
	}

	@Test
	public void shouldPutValidUserInSession()
	{
		// given
		IUser realUser = createLoginBeanWithValidUser();

		// when
		loginBean.validateUser();

		// then
		verify(userSession).setUser(realUser);
	}

	@Test
	public void shouldDisplayErrorMessageForWrongUser()
	{
		// given
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
		Optional<IRememberedUser> rememberedUser = Optional.of(createValidRememberdUser());

		when
			(rememberMeService.getRememberedUser()).
		thenReturn
			(rememberedUser);

		createEmptyLoginBean();

		// when
		loginBean.init();

		// then
		assertThat(loginBean.getLogin(), is(equalTo(userLogin)));
		assertThat(loginBean.getPassword(), is(equalTo(userPassword)));
		assertThat(loginBean.isRememberMe(), is(equalTo(true)));
	}

	private IRememberedUser createValidRememberdUser() {
		return new RememberedUser(userLogin, userPassword);
	}
}
