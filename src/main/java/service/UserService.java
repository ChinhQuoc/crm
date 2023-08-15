package service;

import repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	
	public boolean addUser(String fullname, String password, String phone, String email, int idRole) {
		int count = userRepository.insert(fullname, password, phone, email, idRole);
		
		return count > 0;
	}
}
