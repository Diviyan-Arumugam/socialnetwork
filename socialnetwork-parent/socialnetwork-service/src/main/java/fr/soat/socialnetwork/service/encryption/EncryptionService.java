package fr.soat.socialnetwork.service.encryption;

import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@ApplicationScoped
@Default
public class EncryptionService implements IEncryptionService {

	private final ICipherProvider cipherProvider;
	private final ISecretKeyProvider keyProvider;

	protected EncryptionService()
	{ cipherProvider = null; keyProvider = null; }

	@Inject
	public EncryptionService(ICipherProvider cipherProvider,
							 ISecretKeyProvider keyProvider)
	{
		this.cipherProvider = cipherProvider;
		this.keyProvider = keyProvider;
	}

	public String encrypt(String plainText) throws EncryptionServiceException {
		Cipher cipher = cipherProvider.getCipher();
		SecretKey secretKey = keyProvider.getSecretKey();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			throw new EncryptionServiceException(e);
		}
		byte[] cipherText;
		try {
			cipherText = cipher.doFinal(plainText.getBytes());
		} catch (IllegalBlockSizeException e) {
			throw new EncryptionServiceException(e);
		} catch (BadPaddingException e) {
			throw new EncryptionServiceException(e);
		}

		return new String(cipherText);
	}

	public String decrypt(String encryptedText) throws EncryptionServiceException {
		Cipher cipher = cipherProvider.getCipher();
		SecretKey secretKey = keyProvider.getSecretKey();

		byte[] encrypted = encryptedText.getBytes();

        try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			throw new EncryptionServiceException(e);
		}
        byte[] decrypted;
		try {
			decrypted = cipher.doFinal(encrypted);
		} catch (IllegalBlockSizeException e) {
			throw new EncryptionServiceException(e);
		} catch (BadPaddingException e) {
			throw new EncryptionServiceException(e);
		}

		return new String(decrypted);
	}
}
