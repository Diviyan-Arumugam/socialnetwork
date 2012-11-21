package fr.soat.socialnetwork.common.services.encryption;

import javax.crypto.SecretKey;

public interface ISecretKeyProvider {
	SecretKey getSecretKey();
}
