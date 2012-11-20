package fr.soat.socialnetwork.service.encryption;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.soat.socialnetwork.common.services.encryption.EncryptionService;
import fr.soat.socialnetwork.common.services.encryption.EncryptionServiceException;
import fr.soat.socialnetwork.common.services.encryption.ICipherProvider;
import fr.soat.socialnetwork.common.services.encryption.IEncryptionService;
import fr.soat.socialnetwork.common.services.encryption.ISecretKeyProvider;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EncryptionServiceTest {

	@Mock
	private ICipherProvider cipherProvider;

	@Mock
	private ISecretKeyProvider secretKeyProvider;

	private IEncryptionService encryptionService;

	private static final String DECRYPTED = "decrypted";
	private static final String ENCRYPTED = "decrypted";

	@Test
	public void shouldEncryptTextUsingProvidedCipher() throws EncryptionServiceException {
		// given
		createEncryptionService();
		stubCollaborators();

		// when
		String value = encryptionService.encrypt(DECRYPTED);

		// then
		assertThat(value, is(equalTo(DECRYPTED))); // using null cipher
	}

	@Test
	public void shouldDecryptTextUsingProvidedCipher() throws EncryptionServiceException {
		// given
		createEncryptionService();
		stubCollaborators();

		// when
		String value = encryptionService.decrypt(ENCRYPTED);

		// then
		assertThat(value, is(equalTo(ENCRYPTED))); // using null cipher
	}

	private void stubCollaborators() {
		stubCipherProvider();
		stubSecretKeyProvider();
	}

	private void stubSecretKeyProvider() {
	}

	private void stubCipherProvider() {
		Cipher cipher = new NullCipher();
		when
			(cipherProvider.getCipher()).
		thenReturn
			(cipher);
	}

	private void createEncryptionService() {
		encryptionService = new EncryptionService(cipherProvider, secretKeyProvider);
	}
}
