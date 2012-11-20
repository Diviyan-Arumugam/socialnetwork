package fr.soat.socialnetwork.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.test.junit4.AbstractCdiAwareTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.soat.socialnetwork.bo.IUser;
import fr.soat.socialnetwork.bo.User;

@RunWith(JUnit4.class)
public class UserDAOTest extends AbstractCdiAwareTest {

	private final String firstName = "firstName";
	private final String lastName = "lastName";
	private final String email = "guillaume.prehu@soat.fr";
	private final String login = "gprehu";
	private final String password = "password";

	@Inject
	IUserDAO dao;

	@Before
	public void setUp() throws Exception {
		// dao.getEntityManager().getTransaction().begin();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// dao.getEntityManager().getTransaction().rollback();
	}

	@Test
	public void testGetEntityManager() {
		assertThat(dao, is(not(nullValue())));
	}

	@Test
	public void testInsert() {
		IUser user = createUser();
		IUser user2 = save(user);
		Assert.assertNotNull(user2);
	}

	@Test
	public void testFindByLoginPassword() {
		IUser user = save(createUser());
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
		IUser user = save(createUser());
		user.setEmail("toto");
		IUser userStored = dao.update(user);
		Assert.assertNotNull(userStored);
		Assert.assertNotNull(userStored.getEmail());
		Assert.assertTrue(user.getEmail().equals(userStored.getEmail()));
	}

	private IUser createUser() {
		IUser user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLogin(login);
		user.setPassword(password);
		return user;
	}

	private IUser save(IUser user) {
		try {
			user = dao.save(user);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return user;
	}

}