package service;

import java.util.List;

import entity.User;
import repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public boolean addUser(String firstName, String lastName, String userName, String fullname, String password,
			String phone, String email, int idRole) {
		int count = userRepository.insert(firstName, lastName, userName, fullname, password, phone, email, idRole);
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

	public boolean updateUser(int id, String firstName, String lastName, String fullName, String userName, String phone,
			int idRole) {
		int count = userRepository.update(id, firstName, lastName, fullName, userName, phone, idRole);
		return count > 0;
	}
	
	public List<User> getUsersByProjectId(int idProject) {
		return userRepository.findUsersByIdJob(idProject);
	}
}
