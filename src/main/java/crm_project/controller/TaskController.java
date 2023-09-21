package crm_project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Job;
import entity.Project;
import entity.User;
import service.GroupWorkService;
import service.JobService;
import service.UserService;

@WebServlet(name = "taskController", urlPatterns = { "/tasks", "/task-add", "/task-edit" })
public class TaskController extends HttpServlet{
	private GroupWorkService groupworkService = new GroupWorkService();
	private UserService userService = new UserService();
	private JobService jobService = new JobService();
	
	private int exIdUser= 0;
	private int exIdProject = 0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/tasks")) {
			List<Job> jobs = new ArrayList<>();
			jobs = jobService.getAllJobs();
			
			req.setAttribute("jobs", jobs);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
		} else if (path.equals("/task-add")) {
			prepareData(req);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		} else if (path.equals("/task-edit")) {
			exIdUser = exIdProject = 0;
			int id = Integer.parseInt(req.getParameter("id"));
			Job job = new Job();
			job = jobService.getById(id);
			
			exIdUser = job.getIdUser();
			exIdProject = job.getIdProject();
			
			req.setAttribute("job", job);
			prepareData(req);
			req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/task-add")) {
			String name = req.getParameter("name");
			String content = req.getParameter("content");
			String startDate = req.getParameter("startDate");
			String enđate = req.getParameter("endDate");
			
			int idUser = Integer.parseInt(req.getParameter("user"));
			int idProject = Integer.parseInt(req.getParameter("project"));
			
			boolean isSuccess = jobService.addJob(name, content, startDate, enđate, idProject, idUser);
			
			req.setAttribute("isSuccess", isSuccess);
			
			prepareData(req);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		} else if (path.equals("/task-edit")) {
			String name = req.getParameter("name");
			String content = req.getParameter("content");
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			int id = Integer.parseInt(req.getParameter("id"));
			int idUser = Integer.parseInt(req.getParameter("user"));
			int idProject = Integer.parseInt(req.getParameter("project"));
			
			boolean isSuccess = jobService.updateJob(id, name, content, startDate, endDate, idProject, idUser, exIdProject, exIdUser);
			
			req.setAttribute("isSuccess", isSuccess);
			
			Job job = new Job();
			job = jobService.getById(id);
			
			req.setAttribute("job", job);
			prepareData(req);
			req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
		}
	}
	
	private void prepareData(HttpServletRequest req) {
		List<Project> projects = new ArrayList<Project>();
		List<User> users = new ArrayList<User>();
		
		projects = groupworkService.getNameProjects();
		users = userService.getNameUsers(); 
		
		req.setAttribute("projects", projects);
		req.setAttribute("users", users);
	}
}
