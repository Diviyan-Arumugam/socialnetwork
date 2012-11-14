package fr.soat.socialnetwork.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserDAO implements IUserDAO {

	public UserDAO() {
		super();
	}

	@Inject
	EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.soat.socialnetwork.dao.IUserDAO#find(long)
	 */
	public UserDTO find(long id) {
		return em.find(UserDTO.class, id);
	}

	public List<User> findAll() {
		Query query = em.createQuery("SELECT e FROM user e");
		return (List<User>) query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.soat.socialnetwork.dao.IUserDAO#save(fr.soat.socialnetwork.dao.User)
	 */
	public UserDTO save(UserDTO entity) {
		System.out.println("test");
		em.persist(entity);
		return em.merge(entity);
	}

	public UserDTO getByEmail(String email) {
		// Session sesion = (Session) em.getDelegate();
		// return (UserDTO) sesion.createCriteria(UserDTO.class).add(
		// Restrictions.eq("email", email)).uniqueResult();
		return new UserDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.soat.socialnetwork.dao.IUserDAO#update(fr.soat.socialnetwork.dao.User)
	 */
	public UserDTO update(UserDTO entity) {
		em.persist(entity);
		return em.merge(entity);
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}