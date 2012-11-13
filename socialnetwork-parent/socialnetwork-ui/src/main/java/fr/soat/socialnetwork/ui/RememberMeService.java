package fr.soat.socialnetwork.ui;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.service.encryption.EncryptionServiceException;
import fr.soat.socialnetwork.service.encryption.IEncryptionService;

@Named
@ApplicationScoped
@Default
public class RememberMeService implements IRememberMeService {

	private IRememberMeCookieManager cookieManager;
	private IEncryptionService encryptionService;

	protected static final String NAME_COOKIE_SUFFIX = "UserName";
	protected static final String PASS_COOKIE_SUFFIX = "Password";

	protected RememberMeService()
	{
	}

	@Inject
	public RememberMeService(IRememberMeCookieManager cookieManager,
							 IEncryptionService encryptionService)
	{
		this.cookieManager = cookieManager;
		this.encryptionService = encryptionService;
	}

	@Override
	public void rememberMe(IUser realUser) throws EncryptionServiceException
	{
		setName(realUser.getEmail());

		String password = realUser.getPassword();
		String encryptedPassword = encryptionService.encrypt(password);
		setEncryptedPassword(encryptedPassword);
	}

	@Override
	public Optional<IRememberedUser> getRememberedUser() throws EncryptionServiceException {
		Optional<IRememberedUser> rememberedUser;

		String name = getName();
		String encryptedPassword = getEncryptedPassword();
		if ((Strings.isNullOrEmpty(name)) ||
			(Strings.isNullOrEmpty(encryptedPassword)))
		{
			rememberedUser = Optional.absent();
		}
		else
		{
			String password = encryptionService.decrypt(encryptedPassword);
			rememberedUser = Optional.of(createRememberedUser(name, password));
		}
		return rememberedUser;
	}

	public IRememberedUser createRememberedUser(String name, String password)
	{
		return new RememberedUser(name, password);
	}

	private void setName(String name)
	{
		cookieManager.addCookie(NAME_COOKIE_SUFFIX, name);
	}

	private void setEncryptedPassword(String encryptedPassword)
	{
		byte[] encoded = Base64.encodeBase64(encryptedPassword.getBytes());
		cookieManager.addCookie(PASS_COOKIE_SUFFIX, new String(encoded));
	}

	private String getName()
	{
		return cookieManager.getCookie(NAME_COOKIE_SUFFIX);
	}

	private String getEncryptedPassword()
	{
		String encryptedPassword;
		String base64encrypted = cookieManager.getCookie(PASS_COOKIE_SUFFIX);
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
}
