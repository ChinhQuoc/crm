package crm_project.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Job;
import entity.User;
import service.JobService;

@WebServlet(name = "tasksUserProfileController", urlPatterns = {"/tasks-user"})
public class TasksUserProfileController extends HttpServlet{
	private final int HASNT_STARTED = 1;
	private final int STARTING = 2;
	private final int STARTED = 3;
	
	private JobService jobService = new JobService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("LOGIN_USER");
		int idUser = currentUser.getId();
		List<Job> jobs = jobService.getByIdUser(idUser);
		req.setAttribute("jobs", jobs);
		
		setPercentForEachStatus(req, jobs);
		
		req.getRequestDispatcher("tasks-user-profile.jsp").forward(req, resp);
	}
	
	private void setPercentForEachStatus(HttpServletRequest req, List<Job> jobs) {
		int jobTotal = jobs.size();
		int hasntStated = 0;
		int starting = 0;
		int started = 0;
		
		if (jobTotal != 0) {
			for (Job job : jobs) {
				if (HASNT_STARTED == job.getIdStatus()) {
					hasntStated++;
				} else if (STARTING == job.getIdStatus()) {
					starting++;
				} else if (STARTED == job.getIdStatus()) {
					started++;
				}
			}
			
			req.setAttribute("hasntStated", calculatePercent(jobTotal, hasntStated));
			req.setAttribute("starting", calculatePercent(jobTotal, starting));
			req.setAttribute("started", calculatePercent(jobTotal, started));
		} else {
			req.setAttribute("hasntStated", 0);
			req.setAttribute("starting", 0);
			req.setAttribute("started", 0);
		}
	}
	
	private String calculatePercent(int sum, int statusNumber) {
		DecimalFormat df_obj = new DecimalFormat("#");
		return df_obj.format((statusNumber * 100) / sum);
	}
}
