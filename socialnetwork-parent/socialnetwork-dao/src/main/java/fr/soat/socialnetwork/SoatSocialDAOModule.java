package fr.soat.socialnetwork;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import fr.soat.socialnetwork.dao.IUserDAO;
import fr.soat.socialnetwork.dao.UserDAO;

public class SoatSocialDAOModule extends AbstractModule {

	public void configure() {
		bind(IUserDAO.class).to(UserDAO.class);
	}

	private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();

	@Provides
	@Singleton
	public EntityManagerFactory provideEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("soatsocial");
	}

	@Provides
	public EntityManager provideEntityManager(
			EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
		if (entityManager == null) {
			ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory
					.createEntityManager());
		}
		return entityManager;
	}

}