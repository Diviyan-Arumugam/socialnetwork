package fr.soat.socialnetwork.dao.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserTransfromer {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public IUser getIUserFromUserDTO(UserDTO userDTO) {
		IUser user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setLogin(userDTO.getLogin());
		user.setPassword(userDTO.getPassword());
		return user;
	}

	public UserDTO getUserDTOFromIUser(IUser user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setLogin(user.getLogin());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}

}
