package fr.soat.socialnetwork.ui;

import com.google.common.base.Optional;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.service.encryption.EncryptionServiceException;

public interface IRememberMeService {

	void rememberMe(IUser realUser) throws EncryptionServiceException;
	Optional<IRememberedUser> getRememberedUser() throws EncryptionServiceException;
	void forgetMe(IUser realUser);
}
