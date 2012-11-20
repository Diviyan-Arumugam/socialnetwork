package fr.soat.socialnetwork.dao.mapper;

import java.util.List;

import javax.inject.Inject;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.IUserDAO;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserDAOService {
	
	@Inject
	IUserDAO dao;
	
	public IUser getIUser(String email, String password) {
		String firstName = "firstName";
		String lastName = "lastName";
		String email1 = "guillaume.prehu@soat.fr";
		UserDTO user = new UserDTO(firstName, lastName, email1);
		dao.save(user);
		
		return null;
	}

	/**
	 * 
	 */
	private IUser findAll() {
		List<User> userDTO = dao.findAll();
		
		if(userDTO.isEmpty()) {
			return userDTO.get(0);
		}
		return null;
	}
	
	public void resetPassword(String email) {
		
	}
	
	private UserDTO createUser() {
		String firstName = "firstName";
		String lastName = "lastName";
		String email = "guillaume.prehu@soat.fr";
		UserDTO user = new UserDTO(firstName, lastName, email);
		return user;
	}

}
