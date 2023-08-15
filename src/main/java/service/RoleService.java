package service;

import java.util.List;

import entity.Role;
import repository.RoleRepository;

/**
 * Service: chứa những class chuyên xly logic cho controller
 * cách đặt tên: Giống với controller: vd RoleController => RoleService
 * 
 * cách đặt tên hàm: đặt tên hàm ứng với lại chức năng sẽ làm trên giao diện/ bên controller
 *
 */
public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean addRole(String name, String desc) {
		int count = roleRepository.insert(name, desc);
		
		return count > 0;
	}
	
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}
}
