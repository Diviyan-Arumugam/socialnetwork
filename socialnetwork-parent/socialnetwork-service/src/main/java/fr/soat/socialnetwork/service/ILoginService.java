package fr.soat.socialnetwork.service;

public interface ILoginService {
	boolean isValidUser(String userName, String password);
}