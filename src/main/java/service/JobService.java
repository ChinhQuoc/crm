package service;

import java.util.List;

import entity.Job;
import repository.TaskRepository;

public class JobService {
	private TaskRepository taskRepository = new TaskRepository();
	
	public List<Job> getAllJobs() {
		return taskRepository.findAll();
	}
	
	public boolean addJob(String name, String content, String startDate, String endDate, int idProject, int idUser) {
		int result = taskRepository.insert(name, content, startDate, endDate, idProject, idUser);
		return result > 0;
	}
	
	public Job getById(int id) {
		return taskRepository.findById(id);
	}
	
	public boolean updateJob(int id, String name, String content, String startDate, String endDate, int idProject, int idUser,
			int exIdProject, int exIdUser) {
		int count = taskRepository.update(id, name, content, startDate, endDate, idProject, idUser, exIdProject, exIdUser);
		return count > 0;
	}
	
	public boolean deleteJob(int id, int idProject, int idUser) {
		int count = taskRepository.deleteById(id, idProject, idUser);
		return count > 0;
	}
}
