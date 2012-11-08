package fr.soat.socialnetwork.bo;

public class WrongUser implements IUser {

	public boolean isValidUser() {
		return false;
	}

	public String getName() {
		return "";
	}

	public String getPassword() {
		return "";
	}

}
