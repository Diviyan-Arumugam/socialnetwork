package fr.soat.socialnetwork.dao.mapper;

import javax.inject.Inject;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.bo.WrongUser;
import fr.soat.socialnetwork.dao.IUserDAO;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserDAOService {
	
	@Inject
	IUserDAO dao;
	
	public IUser getIUser(String email, String password) {
		UserDTO userDTO = dao.find(2);
		
		return getIuserFromUserDTO(userDTO);
	}
	
	public void resetPassword(String email) {
		
	}
	
	private IUser getIuserFromUserDTO(UserDTO userDTO) {
		if(userDTO ==null) {
			return new WrongUser();
		}
		IUser user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setLogin(userDTO.getLogin());
		return user;
	}

}
