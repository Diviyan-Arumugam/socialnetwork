package fr.soat.socialnetwork.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;
import fr.soat.socialnetwork.dao.entity.UserDTO;
import fr.soat.socialnetwork.dao.transformer.UserTransfromer;
import fr.soat.socialnetwork.service.encryption.EncryptionServiceException;
import fr.soat.socialnetwork.service.encryption.IEncryptionService;

public class UserDAO implements IUserDAO {

	@PersistenceContext(unitName = "soatsocial")
	private EntityManager em;

	@Inject
	IEncryptionService encryptionService;
	
	@Inject UserTransfromer transformer;

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
		// em.getTransaction().commit();
		// return entity;
		return em.merge(entity);
	}

	public IUser findByLoginPassword(String login, String password)
			throws DAOException {
		
		String encryptedPass = null;
		try {
			encryptedPass = encryptionService.encrypt(password);
		} catch (EncryptionServiceException e) {
			throw new DAOException(e);
		}

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		Root<UserDTO> user = criteriaQuery.from(UserDTO.class);
		criteriaQuery.where(criteriaBuilder.equal(user.get("login"), login), criteriaBuilder.equal(user.get("password"), encryptedPass));
		CriteriaQuery<Object> select = criteriaQuery.select(user);
		
		TypedQuery<Object> typedQuery = em.createQuery(select);
		List<Object> resultList = typedQuery.getResultList();
		UserDTO userdto = (UserDTO) resultList.get(0);
		
		return transformer.getIUserFromUserDTO(userdto);
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

	public List<User> findAll() {
		Query query = em.createQuery("SELECT e FROM user e");
		return query.getResultList();
	}

}