package service;

import java.util.List;

import entity.User;
import repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public boolean addUser(String fullname, String password, String phone, String email, int idRole) {
		int count = userRepository.insert(fullname, password, phone, email, idRole);
		
		return count > 0;
	}
	
	public boolean deleteUser(int id) {
		int count = userRepository.deleteById(id);
		
		return count > 0;
	}
	
	public List<User> getNameUsers() {
		return userRepository.findNameUsers();
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id);
	}
}
