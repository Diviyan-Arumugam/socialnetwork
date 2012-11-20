package fr.soat.socialnetwork.service.encryption;

import javax.crypto.Cipher;

public interface ICipherProvider {
	Cipher getCipher();
}
