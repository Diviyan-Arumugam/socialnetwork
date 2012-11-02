package fr.soat.socialnetwork.ui.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.myfaces.el.unified.resolver.GuiceResolver;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctx = event.getServletContext();
		Injector injector = getInjector();
		ctx.setAttribute(GuiceResolver.KEY, injector);
	}

	private Injector getInjector() {
		return Guice.createInjector(new SocialNetworkUIGuiceModule());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		 ServletContext ctx = event.getServletContext();
		 ctx.removeAttribute(GuiceResolver.KEY);
	}
	
}
