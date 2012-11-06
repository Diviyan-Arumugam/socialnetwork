package fr.soat.socialnetwork.doa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import fr.soat.socialnetwork.SoatSocialDAOModule;
import fr.soat.socialnetwork.dao.UserDAO;
import fr.soat.socialnetwork.dao.entity.User;

public class UserDAOTest {

	@Inject
	UserDAO dao;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new SoatSocialDAOModule());
		injector.injectMembers(this);
		dao.getEntityManager().getTransaction().begin();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dao.getEntityManager().getTransaction().rollback();
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setFirstName("user1");
		user.setLastName("password");
		dao.save(user);
		Assert.assertNotNull(user.getId());
	}
	//
	// @Test
	// public void testFindsetUsername("user1");
	// user.setPassword("password");
	// user.setStatus(Boolean.TRUE);
	// Long id = dao.insert(user);
	// Assert.assertNotNull(dao.find(id));
	// }
	//
	// @Test
	// public void testUpdate() {
	// User user = new User();
	// user.setUsername("user1");
	// user.setPassword("password");
	// user.setStatus(Boolean.TRUE);
	// Long id = dao.insert(user);
	// user.setUsername("user2");
	// User user2 = dao.find(id);
	// assertEquals("user2", user2.getUsername());
	// }
	//
	//
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
	// public void testGetEntityManager() {
	// Assert.assertNotNull(dao.getEntityManager());
	// }
	//
	//
	// @Test
	// public void testGetEntityClass() {
	// assertEquals(User.class, dao.getEntityClass());
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