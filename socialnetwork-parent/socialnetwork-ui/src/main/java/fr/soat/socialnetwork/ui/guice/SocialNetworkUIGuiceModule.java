package fr.soat.socialnetwork.ui.guice;

import com.google.inject.AbstractModule;

import fr.soat.socialnetwork.service.ILoginService;
import fr.soat.socialnetwork.service.LoginService;

public class SocialNetworkUIGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ILoginService.class).to(LoginService.class);
	}
}
