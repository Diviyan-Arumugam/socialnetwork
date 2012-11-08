package fr.soat.socialnetwork.bo;

public class User implements IUser {

	private String name;
	private String password;
	
	public boolean isValidUser() {
		return true;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

}
