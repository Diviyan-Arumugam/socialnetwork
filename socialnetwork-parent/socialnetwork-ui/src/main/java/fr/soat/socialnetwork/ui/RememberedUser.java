package fr.soat.socialnetwork.ui;

public class RememberedUser implements IRememberedUser {

	private final String login;
	private final String password;

	public RememberedUser(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getPassword() {
		return password;
	}

}
