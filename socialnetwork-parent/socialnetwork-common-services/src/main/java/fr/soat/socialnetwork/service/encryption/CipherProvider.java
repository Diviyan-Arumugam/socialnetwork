package fr.soat.socialnetwork.service.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.enterprise.inject.Produces;

public class CipherProvider implements ICipherProvider {

	private Cipher cipher;

	private static final String ALGORITHM_NAME = "AES"; // Advanced Encryption Standard
	private static final String ALGORITHM_MODE = "ECB"; // Electronic Codebook Mode
	private static final String ALGORITHM_PADDING = "PKCS5Padding";

	private static final String TRANSFORMATION = ALGORITHM_NAME + "/" +
												 ALGORITHM_MODE + "/" +
												 ALGORITHM_PADDING;

	private static final String PROVIDER = "SunJCE";

	public CipherProvider()
	{
	}

	@PostConstruct
	public void init()
	{
		try {
			cipher = Cipher.getInstance(TRANSFORMATION, PROVIDER);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchProviderException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchPaddingException e) {
			throw new IllegalStateException(e);
		}
	}

	@Produces
	public Cipher getCipher() {

		return cipher;
	}
}
