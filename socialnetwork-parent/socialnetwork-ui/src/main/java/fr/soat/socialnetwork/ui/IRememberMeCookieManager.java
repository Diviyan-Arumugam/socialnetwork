package fr.soat.socialnetwork.ui;

public interface IRememberMeCookieManager {

	String getCookieValue(String cookieName);
	void addCookie(String cookieName, String cookieVariable);
	void removeCookie(String cookieName);
}
