package fr.soat.socialnetwork.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Hello world!
 * 
 */
public class MYSQLManualTest {
	@Test
	public void testDB() {

		System.out
				.println("-------- MySQL JDBC Connection Testing ------------");

		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://ec2-23-21-211-172.compute-1.amazonaws.com/socialnetwork",
							"socialnetwork", "soatsocial");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		Assert.assertTrue("Failed to make connection!", connection!=null);
	}
}
