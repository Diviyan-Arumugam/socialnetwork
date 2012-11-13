package fr.soat.socialnetwork;


public class SoatSocialDAOModule //extends AbstractModule 
{
/**
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
*/
}