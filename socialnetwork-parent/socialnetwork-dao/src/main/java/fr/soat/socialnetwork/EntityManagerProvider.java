package fr.soat.socialnetwork;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EntityManagerProvider {

	@Inject
    protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
