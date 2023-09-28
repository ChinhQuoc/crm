package crm_project.controller;

import java.io.IOException;
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

@WebServlet(name="indexController", urlPatterns = {"/"})
public class IndexController extends HttpServlet {
	private final int HASNT_STARTED = 1;
	private final int STARTING = 2;
	private final int STARTED = 3;
	
	private JobService jobService = new JobService();

	@Override public void init() throws ServletException {
		// TODO Auto-generated method stub 
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("LOGIN_USER");
		
		List<Job> jobs = jobService.getAllJobs();
		setPercentForEachStatus(req, jobs);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
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
			
			req.setAttribute("percentHasntStated", calculatePercent(jobTotal, hasntStated));
			req.setAttribute("percentStarting", calculatePercent(jobTotal, starting));
			req.setAttribute("percentStarted", calculatePercent(jobTotal, started));
		} else {
			req.setAttribute("percentHasntStated", 0);
			req.setAttribute("percentStarting", 0);
			req.setAttribute("percentStarted", 0);
		}
		
		req.setAttribute("hasntStated", hasntStated);
		req.setAttribute("starting", starting);
		req.setAttribute("started", started);
	}
	
	private double calculatePercent(int sum, int statusNumber) {
		return Math.round((statusNumber * 100) / sum);
	}
}
