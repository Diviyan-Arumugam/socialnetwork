package fr.soat.socialnetwork.bo;

import java.util.List;

public interface IUser {

	String getLogin();
	void setLogin(String login);

	String getFirstName() ;
	void setFirstName(String firstName) ;

	String getLastName() ;
	void setLastName(String lastName) ;

	String getEmail() ;
	void setEmail(String email) ;

	String getPassword() ;
	void setPassword(String password) ;

	boolean isValidUser();
	
	public List<Group> getGroups();
	public void setGroups(List<Group> groups);
}
