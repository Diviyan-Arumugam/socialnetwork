package fr.soat.socialnetwork.service.encryption;

public interface IEncryptionService {
	String encrypt(String plainText) throws EncryptionServiceException;
	String decrypt(String encryptedText) throws EncryptionServiceException;
}
