package fr.soat.socialnetwork.bo;

public class User implements IUser {

	private String name;
	private String password;

	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
	}

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
