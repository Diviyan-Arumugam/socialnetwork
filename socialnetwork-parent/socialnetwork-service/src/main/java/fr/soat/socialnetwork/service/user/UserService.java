package fr.soat.socialnetwork.service.user;

import java.util.List;

import javax.inject.Inject;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.DAOException;
import fr.soat.socialnetwork.dao.IUserDAO;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserService implements IUserService {
	
	@Inject
	IUserDAO dao;
	
	//@Inject
	//iencryptio
	
	/* (non-Javadoc)
	 * @see fr.soat.socialnetwork.dao.mapper.IUserService#getIUser(java.lang.String, java.lang.String)
	 */
	public IUser getIUser(String email, String password) {
		createUser();
		
		return null;
	}
	
	public IUser getByEmailPassword(String login, String password) {
		try {
			return dao.findByLoginPassword(login, password);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.soat.socialnetwork.dao.mapper.IUserService#findAll()
	 */
	public IUser findAll() {
		List<User> userDTO = dao.findAll();
		
		if(userDTO.isEmpty()) {
			return userDTO.get(0);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see fr.soat.socialnetwork.dao.mapper.IUserService#resetPassword(java.lang.String)
	 */
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
