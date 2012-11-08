package fr.soat.socialnetwork;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DataBaseProducer
{
    @PersistenceContext(unitName="default")
    private EntityManager entityManager;

    @Produces
    @Default
    @RequestScoped
    public EntityManager createDefaultEntityManager()
    {
        return this.entityManager;
    }

    public void dispose(@Disposes @Default EntityManager entityManager)
    {
        if(entityManager.isOpen())
        {
            entityManager.close();
        }
    }
}