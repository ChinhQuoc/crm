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
		boolean result = taskRepository.insert(name, content, startDate, endDate, idProject, idUser);
		return result;
	}
	
	public Job getById(int id) {
		return taskRepository.findById(id);
	}
	
	public boolean updateJob(int id, String name, String content, String startDate, String endDate, int idProject, int idUser,
			int exIdProject, int exIdUser) {
		boolean isSuccess = taskRepository.update(id, name, content, startDate, endDate, idProject, idUser, exIdProject, exIdUser);
		return isSuccess;
	}
	
	public boolean deleteJob(int id, int idStatus, int idUser) {
		boolean isSuccess = taskRepository.deleteById(id, idStatus, idUser);
		return isSuccess;
	}
	
	public List<Job> getByIdUser(int id) {
		return taskRepository.selectByIdUser(id);
	}
	
	public List<Job> getByIdUserAndIdStatus(int idUser, int idStatus) {
		return taskRepository.findByIdUserAndIdStatus(idUser, idStatus);
	}
}
