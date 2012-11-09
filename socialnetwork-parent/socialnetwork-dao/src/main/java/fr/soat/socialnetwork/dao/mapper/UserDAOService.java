package fr.soat.socialnetwork.dao.mapper;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.dao.IUserDAO;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserDAOService {
	
	@Inject
	IUserDAO dao;
	
	public IUser getIUser(String email, String password) {
		UserDTO userDTO = dao.find(2);
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userDTO, IUser.class);
	}
	
	public void resetPassword(String email) {
		
	}
	
	

}
