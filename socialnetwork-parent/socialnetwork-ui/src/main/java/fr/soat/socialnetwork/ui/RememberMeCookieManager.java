package fr.soat.socialnetwork.ui;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;

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
	public String getCookie(String cookieSuffix) {
		String cookieValue = null;

		ExternalContext externalContext = getExternalContext();
		Map<String, Object> requestCookieMap = externalContext.getRequestCookieMap();
		Cookie cookie = (Cookie) requestCookieMap.get(cookieSuffix);

		if (cookie != null)
		{
			cookieValue = cookie.getValue();
		}

		return cookieValue;
	}

	@Override
	public void addCookie(String cookieSuffix, String cookieVariable) {
		ExternalContext externalContext = getExternalContext();
		externalContext.addResponseCookie(cookieSuffix, cookieVariable, null);
	}

	private ExternalContext getExternalContext() {
		ExternalContext externalContext = context.getExternalContext();
		return externalContext;
	}
}
