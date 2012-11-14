package fr.soat.socialnetwork.ui;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Named
@ApplicationScoped
public class RememberMeCookieManager implements IRememberMeCookieManager {

	private FacesContext context;

	protected RememberMeCookieManager()
	{
	}

	@Inject
	public RememberMeCookieManager(FacesContext context)
	{
		this.context = context;
	}

	@Override
	public String getCookieValue(String cookieName) {
		String cookieValue = null;

		Cookie cookie = getCookie(cookieName);

		if (cookie != null)
		{
			cookieValue = cookie.getValue();
		}

		return cookieValue;
	}

	private Cookie getCookie(String cookieName) {
		ExternalContext externalContext = getExternalContext();
		Map<String, Object> requestCookieMap = externalContext.getRequestCookieMap();
		Cookie cookie = (Cookie) requestCookieMap.get(cookieName);
		return cookie;
	}

	@Override
	public void addCookie(String cookieName, String cookieVariable) {
		ExternalContext externalContext = getExternalContext();
		externalContext.addResponseCookie(cookieName, cookieVariable, null);
	}

	private ExternalContext getExternalContext() {
		ExternalContext externalContext = context.getExternalContext();
		return externalContext;
	}

	@Override
	public void removeCookie(String cookieName) {
		Cookie cookie = getCookie(cookieName);
		cookie.setMaxAge(0);
		cookie.setValue("");

		addResponseCookie(cookie);
	}

	private void addResponseCookie(Cookie cookie) {
		ExternalContext externalContext = getExternalContext();
		HttpServletResponse response =
				(HttpServletResponse) externalContext.getResponse();
		response.addCookie(cookie);
	}
}
