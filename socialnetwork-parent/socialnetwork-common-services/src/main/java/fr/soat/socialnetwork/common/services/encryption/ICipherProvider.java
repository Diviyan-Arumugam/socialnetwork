package fr.soat.socialnetwork.common.services.encryption;

import javax.crypto.Cipher;

public interface ICipherProvider {
	Cipher getCipher();
}
