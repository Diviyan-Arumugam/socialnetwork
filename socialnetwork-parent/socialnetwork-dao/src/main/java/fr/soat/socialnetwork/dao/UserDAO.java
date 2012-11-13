package fr.soat.socialnetwork.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserDAO implements IUserDAO {
	@Inject
	EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.soat.socialnetwork.dao.IUserDAO#find(long)
	 */
	@Transactional
	public UserDTO find(long id) {
		return em.find(UserDTO.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.soat.socialnetwork.dao.IUserDAO#save(fr.soat.socialnetwork.dao.User)
	 */
	@Transactional
	public UserDTO save(UserDTO entity) {
		em.persist(entity);
		return em.merge(entity);
	}

	public UserDTO getByEmail(String email) {
//		Session sesion = (Session) em.getDelegate();
//		return (UserDTO) sesion.createCriteria(UserDTO.class).add(
//				Restrictions.eq("email", email)).uniqueResult();
		return new UserDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.soat.socialnetwork.dao.IUserDAO#update(fr.soat.socialnetwork.dao.User)
	 */
	@Transactional
	public UserDTO update(UserDTO entity) {
		em.persist(entity);
		return em.merge(entity);
	}
}