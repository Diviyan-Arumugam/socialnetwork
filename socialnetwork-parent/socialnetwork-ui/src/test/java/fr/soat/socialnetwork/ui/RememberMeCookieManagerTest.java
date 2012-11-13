package fr.soat.socialnetwork.ui;

import java.util.HashMap;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RememberMeCookieManagerTest {

	private FacesContext facesContext = ContextMocker.mockFacesContext();

	@Mock
	ExternalContext externalContextStub;

	private RememberMeCookieManager rememberMeCookieManager;

	private HashMap<String, Object> requestCookieMap;

	private static final String COOKIE_NAME = "cookieName";
	private static final String COOKIE_VALUE = "cookieValue";

	private void createRememberMeCookieManager()
	{
		rememberMeCookieManager = new RememberMeCookieManager(facesContext);
	}

	private void addCookieMapToExternalContext()
	{
		requestCookieMap = new HashMap<String, Object>();
		when
			(facesContext.getExternalContext()).
		thenReturn
			(externalContextStub);

		when
			(externalContextStub.getRequestCookieMap()).
		thenReturn
			(requestCookieMap);
	}

	@Test
	public void shouldGetCookieValueFromContext() {
		// given
		createRememberMeCookieManager();
		addCookieMapToExternalContext();

		Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
		requestCookieMap.put(COOKIE_NAME, cookie);


		// when
		String value = rememberMeCookieManager.getCookie(COOKIE_NAME);

		// then
		assertThat(value, is(equalTo(COOKIE_VALUE)));
	}

	@Test
	public void shouldSetCookieValueToContext() {
		// given
		createRememberMeCookieManager();
		addCookieMapToExternalContext();

		// when
		rememberMeCookieManager.addCookie(COOKIE_NAME, COOKIE_VALUE);

		// then
		verify(externalContextStub).addResponseCookie(COOKIE_NAME, COOKIE_VALUE, null);
	}

	@Test
	public void shouldReturnNothingWhenNoCookieSet()
	{
		// given
		createRememberMeCookieManager();
		addCookieMapToExternalContext();

		// when
		String value = rememberMeCookieManager.getCookie(COOKIE_NAME);

		// then
		assertThat(value, isEmptyOrNullString() );
	}
}
