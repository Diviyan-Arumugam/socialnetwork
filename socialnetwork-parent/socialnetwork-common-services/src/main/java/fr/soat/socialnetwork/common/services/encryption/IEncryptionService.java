package fr.soat.socialnetwork.common.services.encryption;

public interface IEncryptionService {
	String encrypt(String plainText) throws EncryptionServiceException;
	String decrypt(String encryptedText) throws EncryptionServiceException;
}
