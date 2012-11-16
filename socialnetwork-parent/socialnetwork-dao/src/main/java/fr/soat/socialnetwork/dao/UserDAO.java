package fr.soat.socialnetwork.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserDAO implements IUserDAO {

	@PersistenceContext(unitName = "soatsocial")
    private EntityManager em;

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
	@Override
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
	@Override
	@Transactional
	public UserDTO save(UserDTO entity) {
		em.persist(entity);
		//em.getTransaction().commit();
		//return entity;
		return em.merge(entity);
	}

	@Override
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
	@Override
	@Transactional
	public UserDTO update(UserDTO entity) {
		em.persist(entity);
		return em.merge(entity);
	}

	@Override
	public List<User> findAll() {
		Query query = em.createQuery("SELECT e FROM user e");
		return query.getResultList();
	}
}