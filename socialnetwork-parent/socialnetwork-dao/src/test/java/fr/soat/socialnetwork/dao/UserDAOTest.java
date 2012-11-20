package fr.soat.socialnetwork.dao;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.dao.entity.UserDTO;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class UserDAOTest extends org.apache.myfaces.extensions.cdi.test.junit4.AbstractCdiAwareTest {

	private final String firstName = "firstName";
	private final String lastName = "lastName";
	private final String email = "guillaume.prehu@soat.fr";
	private final String login = "gprehu@soat.fr";
	private final String password = "password";

	@Inject
	UserDAO dao;

	@Before
	public void setUp() throws Exception {
		//dao.getEntityManager().getTransaction().begin();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//dao.getEntityManager().getTransaction().rollback();
	}

	@Test
	public void testGetEntityManager() {
		assertThat(dao.getEntityManager(), is(not(nullValue())));
	}

	@Test
	public void testInsert() {
		dao.getEntityManager().getTransaction().begin();
		UserDTO user = createUser();
		dao.getEntityManager().getTransaction().commit();
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
		IUser userStored = null;
		try {
			userStored = dao.findByLoginPassword(login, password);
		} catch (DAOException e) {
			e.printStackTrace();
		}
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