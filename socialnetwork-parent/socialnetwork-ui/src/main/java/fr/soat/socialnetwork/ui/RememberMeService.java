package fr.soat.socialnetwork.ui;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.common.services.encryption.EncryptionServiceException;
import fr.soat.socialnetwork.common.services.encryption.IEncryptionService;

@Named
@ApplicationScoped
@Default
public class RememberMeService implements IRememberMeService {

	private IRememberMeCookieManager cookieManager;
	@Inject
	private IEncryptionService encryptionService;

	protected static final String NAME_COOKIE_PREFIX = "SoatSocial_UserName";
	protected static final String PASS_COOKIE_PREFIX = "SoatSocial_Password";

	protected RememberMeService()
	{
	}

	@Inject
	public RememberMeService(IRememberMeCookieManager cookieManager)
	{
		this.cookieManager = cookieManager;
	}

	@Override
	public void rememberMe(IUser realUser) throws EncryptionServiceException
	{
		setLogin(realUser.getLogin());
		setEncryptedPassword(realUser.getPassword());
	}

	@Override
	public Optional<IRememberedUser> getRememberedUser() throws EncryptionServiceException {
		Optional<IRememberedUser> rememberedUser;

		String login = getLogin();
		String encryptedPassword = getEncryptedPassword();
		if ((Strings.isNullOrEmpty(login)) ||
			(Strings.isNullOrEmpty(encryptedPassword)))
		{
			rememberedUser = Optional.absent();
		}
		else
		{
			String password = encryptionService.decrypt(encryptedPassword);
			rememberedUser = Optional.of(createRememberedUser(login, password));
		}
		return rememberedUser;
	}

	public IRememberedUser createRememberedUser(String login, String password)
	{
		return new RememberedUser(login, password);
	}

	private void setLogin(String login)
	{
		cookieManager.addCookie(NAME_COOKIE_PREFIX, login);
	}

	private void setEncryptedPassword(String encryptedPassword)
	{
		byte[] encoded = Base64.encodeBase64(encryptedPassword.getBytes());
		cookieManager.addCookie(PASS_COOKIE_PREFIX, new String(encoded));
	}

	private String getLogin()
	{
		return cookieManager.getCookieValue(NAME_COOKIE_PREFIX);
	}

	private String getEncryptedPassword()
	{
		String encryptedPassword;
		String base64encrypted = cookieManager.getCookieValue(PASS_COOKIE_PREFIX);
		if (Strings.isNullOrEmpty(base64encrypted))
		{
			encryptedPassword = new String();
		}
		else
		{
			byte[] base64decoded = Base64.decodeBase64(base64encrypted.getBytes());
			encryptedPassword = new String(base64decoded);
		}

		return encryptedPassword;
	}

	@Override
	public void forgetMe(IUser realUser) {
		cookieManager.removeCookie(NAME_COOKIE_PREFIX);
		cookieManager.removeCookie(PASS_COOKIE_PREFIX);
	}
}
