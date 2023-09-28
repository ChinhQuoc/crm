package service;

import java.util.List;

import entity.Status;
import repository.StatusRepository;

public class StatusService {
	private StatusRepository statusRepository = new StatusRepository();
	
	public List<Status> getAll() {
		return statusRepository.findAll();
	}
}
