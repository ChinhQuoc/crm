package crm_project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySqlConfig;
import entity.Job;
import entity.Project;
import entity.User;
import service.GroupWorkService;
import service.JobService;
import service.UserService;

@WebServlet(name = "groupWorkController", urlPatterns = {"/groupwork-add", "/groupwork", "/groupwork-detail", "/groupwork-edit"})
public class GroupWorkController extends HttpServlet{
	private final int HASNT_STARTED = 1;
	private final int STARTING = 2;
	private final int STARTED = 3;
	
	private GroupWorkService groupWorkService = new GroupWorkService();
	private UserService userService = new UserService();
	private JobService jobService = new JobService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/groupwork-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (path.equals("/groupwork")) {
			List<Project> projects = new ArrayList<Project>();
			
			projects = groupWorkService.getAllProject();
			
			req.setAttribute("projects", projects);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		} else if (path.equals("/groupwork-detail")) {
			int idProject = Integer.parseInt(req.getParameter("id"));
			
			List<User> users = new ArrayList<>();
			users = userService.getUsersByProjectId(idProject);
			
			int totalJobs = 0;
			int totalHasntStatedJobs = 0;
			int totalStartingJobs = 0;
			int totalStatedJobs = 0;
			
			for (User user : users) {
				int idUser = user.getId();
				List<Job> jobs1 = getJobsByIdUserAndIdStatus(idUser, HASNT_STARTED);
				totalHasntStatedJobs += jobs1.size();
				user.setHasntStartedJobs(jobs1);
				
				List<Job> jobs2 = getJobsByIdUserAndIdStatus(idUser, STARTING);
				totalStartingJobs += jobs2.size();
				user.setStartingJobs(jobs2);
				
				List<Job> jobs3 = getJobsByIdUserAndIdStatus(idUser, STARTED);
				totalStatedJobs += jobs3.size();
				user.setStartedJobs(jobs3);
			}
			
			totalJobs = totalHasntStatedJobs + totalStartingJobs + totalStatedJobs;
			
			setPercentForEachStatus(req, totalJobs, totalHasntStatedJobs, totalStartingJobs, totalStatedJobs);
			req.setAttribute("users", users);
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
		} else if (path.equals("/groupwork-edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Project project = new Project();
			project = groupWorkService.getById(id);
			req.setAttribute("project", project);
			req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		String name = req.getParameter("name");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		if (path.equals("/groupwork-add")) {
			boolean isSuccess = groupWorkService.addProject(name, startDate, endDate);
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (path.equals("/groupwork-edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			
			boolean isSuccess = groupWorkService.editProject(id, name, startDate, endDate);
			req.setAttribute("isSuccess", isSuccess);
			
			// get info project after edited
			Project project = new Project();
			project = groupWorkService.getById(id);
			req.setAttribute("project", project);
			req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
		}
	}
	
	private List<Job> getJobsByIdUserAndIdStatus(int idUser, int idStatus) {
		List<Job> jobs = new ArrayList<>();
		jobs = jobService.getByIdUserAndIdStatus(idUser, idStatus);
		return jobs;
	}
	
	private void setPercentForEachStatus(HttpServletRequest req, int total, int totalHasntStatedJobs,
			int totalStartingJobs, int totalStatedJobs) {
		if (total == 0) {
			req.setAttribute("hasntStated", 0);
			req.setAttribute("starting", 0);
			req.setAttribute("started", 0);
		} else {
			req.setAttribute("hasntStated", calculatePercent(total, totalHasntStatedJobs));
			req.setAttribute("starting", calculatePercent(total, totalStartingJobs));
			req.setAttribute("started", calculatePercent(total, totalStatedJobs));
		}
	}
	
	private String calculatePercent(int sum, int statusNumber) {
		DecimalFormat df_obj = new DecimalFormat("#");
		return df_obj.format((statusNumber * 100) / sum);
	}
}
