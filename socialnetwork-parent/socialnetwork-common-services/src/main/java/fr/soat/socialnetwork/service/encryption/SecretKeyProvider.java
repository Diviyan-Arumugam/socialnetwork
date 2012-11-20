package fr.soat.socialnetwork.service.encryption;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.inject.Produces;

public class SecretKeyProvider implements ISecretKeyProvider {

	private static final String ALGORITHM = "AES";

	private static final byte[] key = {73, 64, 4, 99, 115, -28, 15, -115, -71, 39, -38, -105, -100, 94, -21, -89};

	private SecretKey secretKey;

	public SecretKeyProvider()
	{
	}

	@PostConstruct
	public void init()
	{
		secretKey = new SecretKeySpec(key, ALGORITHM);
	}

	@Produces
	public SecretKey getSecretKey() {
		return secretKey;
	}
}
