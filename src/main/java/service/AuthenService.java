package service;

import java.util.List;

import entity.User;
import repository.AuthenRepository;

public class AuthenService {
	private AuthenRepository authenRepository = new AuthenRepository();
	
	public List<User> login(String email, String password) {
		List<User> users = authenRepository.login(email, password);
		return users;
	}
}
