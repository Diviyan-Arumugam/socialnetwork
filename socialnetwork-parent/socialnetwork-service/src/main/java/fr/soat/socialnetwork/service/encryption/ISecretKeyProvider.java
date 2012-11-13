package fr.soat.socialnetwork.service.encryption;

import javax.crypto.SecretKey;

public interface ISecretKeyProvider {
	SecretKey getSecretKey();
}
