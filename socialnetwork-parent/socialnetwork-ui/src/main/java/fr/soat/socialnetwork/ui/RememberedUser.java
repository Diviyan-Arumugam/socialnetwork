package fr.soat.socialnetwork.ui;

public class RememberedUser implements IRememberedUser {

	private final String name;
	private final String password;

	public RememberedUser(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}

}
