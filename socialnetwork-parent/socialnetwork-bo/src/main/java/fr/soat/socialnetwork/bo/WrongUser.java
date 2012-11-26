package fr.soat.socialnetwork.bo;

import java.util.List;

public class WrongUser implements IUser {

	public boolean isValidUser() {
		return false;
	}

	public String getFirstName() {
		return null;
	}

	public void setFirstName(String firstName) {
	}

	public String getLastName() {
		return null;
	}

	public void setLastName(String lastName) {
	}

	public String getEmail() {
		return null;
	}

	public void setEmail(String email) {
	}

	public String getPassword() {
		return null;
	}

	public void setPassword(String password) {
	}

	public String getLogin() {
		return null;
	}

	public void setLogin(String login) {
	}

	public List<IGroup> getGroups() {
		return null;
	}

	public void setGroups(List<IGroup> groups) {
	}
}
