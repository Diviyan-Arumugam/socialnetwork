package fr.soat.socialnetwork.ui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.WrongUser;
import fr.soat.socialnetwork.service.ILoginService;
import fr.soat.socialnetwork.ui.LoginBean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LoginBeanTest {

	@Mock
	private ILoginService loginService;
	
	@Mock
	private UserSessionBean userSession;
	
	private FacesContext facesContext = ContextMocker.mockFacesContext();
	
	private static final String userName = "name";
	private static final String userPassword = "password";
	
	private LoginBean loginBean;
	
	private void createLoginBean() {
		loginBean = new LoginBean(loginService, facesContext, userSession);
		loginBean.setUser(userName);
		loginBean.setPassword(userPassword);
	}
	
	@Test
	public void shouldNotValidateWrongUser()
	{
		// given
		IUser wrongUser = new WrongUser();
		when
			(loginService.getUser(userName, userPassword)).
		thenReturn
			(wrongUser);
		
		createLoginBean();
		
		// when
		boolean result = loginBean.validateUser();
		
		// then
		assertThat(result, is(equalTo(false)));
	}


	@Test
	public void shouldValidateRealUser()
	{
		// given
		IUser realUser = mock(IUser.class);
		when
			(realUser.isValidUser()).
		thenReturn
			(true);
		when
			(loginService.getUser(userName, userPassword)).
		thenReturn
			(realUser);
		
		createLoginBean();
		
		// when
		boolean result = loginBean.validateUser();
		
		// then
		assertThat(result, is(equalTo(true)));
	}
	
	@Test
	public void shouldPutValidUserInSession()
	{
		// given
		IUser realUser = mock(IUser.class);
		when
			(realUser.isValidUser()).
		thenReturn
			(true);
		when
			(loginService.getUser(userName, userPassword)).
		thenReturn
			(realUser);
		
		createLoginBean();

		// when
		loginBean.validateUser();
		
		// then
		verify(userSession).setUser(realUser);
	}
	
	@Test
	public void shouldDisplayErrorMessageForWrongUser()
	{
		// given
		IUser wrongUser = new WrongUser();
		when
			(loginService.getUser(userName, userPassword)).
		thenReturn
			(wrongUser);
		
		createLoginBean();
		
		// when
		loginBean.validateUser();
		
		// then
		verify(facesContext).addMessage(isNull(String.class), isNotNull(FacesMessage.class));
	}

}
