package fr.soat.socialnetwork.ui;

public interface IRememberMeCookieManager {

	String getCookie(String cookieSuffix);
	void addCookie(String cookieSuffix, String cookieVariable);
}
