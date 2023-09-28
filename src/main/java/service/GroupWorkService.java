package service;

import java.util.List;

import entity.Project;
import repository.GroupWorkRepository;

public class GroupWorkService {
	private GroupWorkRepository groupWorkRepository = new GroupWorkRepository();
	
	public List<Project> getAllProject() {
		return groupWorkRepository.findAll();
	}
	
	public boolean addProject(String name, String startDate, String endDate) {
		int count = groupWorkRepository.insert(name, startDate, endDate);
		
		return count > 0;
	}
	
	public List<Project> getNameProjects() {
		return groupWorkRepository.findNameProjects();
	}
	
	public Project getById(int id) {
		return groupWorkRepository.findById(id);
	}
	
	public boolean editProject(int id, String name, String startDate, String endDate) {
		int count = groupWorkRepository.update(id, name, startDate, endDate);
		return count > 0;
	}
	
	public boolean deleteProject(int id) {
		int count = groupWorkRepository.deleteById(id);
		return count > 0;
	}
	
	public boolean editStatus(int id, int idStatus) {
		int count = groupWorkRepository.updateStatus(id, idStatus);
		return count > 0;
	}
}