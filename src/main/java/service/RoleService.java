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
	
	public Role findRoleById(int id) {
		return roleRepository.findById(id);
	}
	
	public boolean editRole(Role role) {
		int count = roleRepository.update(role);
		return count > 0;
	}
	
	public boolean deleteRole(int id) {
		int count = roleRepository.deleteById(id);
		return count > 0;
	}
}
