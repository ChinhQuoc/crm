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
	
	public String deleteUser(String id) {
		int count = userRepository.delete(id);
		
		if (count > 0) {
			return "Xóa user thành công";
		}
		
		return "Xóa user không thành công";
	}
}
