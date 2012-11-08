package fr.soat.socialnetwork.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import fr.soat.socialnetwork.bo.IUser;

@RunWith(MockitoJUnitRunner.class)
public class RememberMeServiceTest {

	@Mock
	private IRememberMeService rememberMeService;
	
	private static final String userName = "name";	

	@Test
	public void shouldStoreUserNameInCookie()
	{
		// given
		IUser user = mock(IUser.class);
		when
			(user.getName()).
		thenReturn
			(userName);

		// when
		rememberMeService.rememberMe(user);
		
		// then
	}

}
