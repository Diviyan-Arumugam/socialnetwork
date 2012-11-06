package fr.soat.socialnetwork.dao;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import fr.soat.socialnetwork.dao.entity.User;

public class UserDAO implements IUserDAO {
    @Inject EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/* (non-Javadoc)
	 * @see fr.soat.socialnetwork.dao.IUserDAO#find(long)
	 */
	@Transactional
    public User find(long id) {
        return em.find(User.class, id);
    }
 
    /* (non-Javadoc)
	 * @see fr.soat.socialnetwork.dao.IUserDAO#save(fr.soat.socialnetwork.dao.User)
	 */
    @Transactional
    public void save(User entity) {
        em.persist(entity);
    }
    
    /* (non-Javadoc)
	 * @see fr.soat.socialnetwork.dao.IUserDAO#getByEmail(java.lang.String)
	 */
    public User getByEmail(String email) {
        return (User) em
            .createQuery("select firstname from users e where e.email=:email")
            .setParameter("email", email)
            .getSingleResult();
      }
}