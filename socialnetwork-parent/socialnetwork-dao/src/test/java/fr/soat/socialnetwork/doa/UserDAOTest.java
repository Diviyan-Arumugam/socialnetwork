package fr.soat.socialnetwork.doa;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.soat.socialnetwork.dao.UserDAO;
import fr.soat.socialnetwork.dao.entity.UserDTO;

public class UserDAOTest {
	
	private final String firstName = "firstName";
	private final String lastName = "lastName";
	private final String email = "guillaume.prehu@soat.fr";

	@Inject
	UserDAO dao;

	@Before
	public void setUp() throws Exception {
//		Injector injector = Guice.createInjector(new SoatSocialDAOModule());
//		dao = injector.getInstance(UserDAO.class);
//		dao.getEntityManager().getTransaction().begin();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
//		dao.getEntityManager().getTransaction().rollback();
	}

	@Test
	public void testGetEntityManager() {
		Assert.assertNotNull(dao.getEntityManager());
	}
	
	@Test
	public void testInsert() {
		UserDTO user = createUser();
		Assert.assertNotNull(user.getId());
	}

	private UserDTO createUser() {
		UserDTO user = new UserDTO(firstName, lastName, email);
		dao.save(user);
		return user;
	}

	@Test
	public void testFindByEmail() {
		UserDTO user = createUser();
		dao.save(user);
		UserDTO userStored = dao.getByEmail(email);
		Assert.assertNotNull(userStored);
		Assert.assertEquals(user, userStored);
	}

	@Test
	public void testUpdate() {
		UserDTO user = createUser();
		UserDTO userStored = dao.save(user);
		Assert.assertNotNull(userStored);
		Assert.assertNotNull(userStored.getId());
		userStored.setFirstName("user2");
		user = dao.update(userStored);
		Assert.assertEquals(userStored, user);
	}

	// @Test
	// public void testDelete() {
	// User user = new User();
	// user.setUsername("user1");
	// user.setPassword("password");
	// user.setStatus(Boolean.TRUE);
	// Long id = dao.insert(user);
	// dao.delete(user);
	// Assert.assertNull(dao.find(id));
	// }
	//
	//
	

	// @Test
	// public void testFindAll() {
	// User user = new User();
	// user.setUsername("user1");
	// user.setPassword("password");
	// user.setStatus(Boolean.TRUE);
	// dao.insert(user);
	//
	// User user2 = new User();
	// user2.setUsername("user2");
	// user2.setPassword("password");
	// user2.setStatus(Boolean.TRUE);
	// dao.insert(user2);
	//
	// assertEquals(2, dao.findAll().size());
	// }
}