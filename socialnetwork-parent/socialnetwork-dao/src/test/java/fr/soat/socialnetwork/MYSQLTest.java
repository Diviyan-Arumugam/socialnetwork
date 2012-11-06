package fr.soat.socialnetwork;

import java.sql.SQLException;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.soat.socialnetwork.SoatSocialDAOModule;
import fr.soat.socialnetwork.dao.UserDAO;
import fr.soat.socialnetwork.dao.entity.User;

public class MYSQLTest {

	@Test
	public void testDb() throws SQLException {
		Injector injector = Guice.createInjector(new SoatSocialDAOModule());
		
	    UserDAO userDAO = injector.getInstance(UserDAO.class);   

		User example = new User();
		example.setEmail("guillaume.prehu@soat.fr");
		example.setFirstName("guillaume");
		example.setLastName("prehu");
		userDAO.save(example);
//
//		User userStored = examplePersistedClassDao
//				.getByEmail("guillaume.prehu@soat.fr");
//
//		Assert.assertEquals(example.getId(), userStored.getId());
//		Assert.assertEquals(example.getEmail(), userStored.getEmail());
	}

}