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
}
