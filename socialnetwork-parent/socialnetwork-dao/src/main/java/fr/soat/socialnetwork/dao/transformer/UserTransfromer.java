package fr.soat.socialnetwork.dao.transformer;

import org.modelmapper.ModelMapper;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserTransfromer {
	
	public IUser getIUserFromUserDTO(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		IUser user = modelMapper.map(userDTO, IUser.class);
		return user;
	}

	public UserDTO getUserDTOFromIUser(IUser user) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

}
