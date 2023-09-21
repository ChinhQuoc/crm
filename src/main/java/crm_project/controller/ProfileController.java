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
import service.UserService;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "profile-edit"})
public class ProfileController extends HttpServlet{
	private UserService userService = new UserService();
	private JobService jobService = new JobService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("account");
		System.out.println(currentUser);
		
		int idUser = currentUser != null ? currentUser.getId() : 14;
		
		if (path.equals("/profile")) {
			User user = new User();
			user = userService.getUserById(idUser);
			
			List<Job> jobs = jobService.getByIdUser(idUser);
			
			req.setAttribute("user", user);
			req.setAttribute("jobs", jobs);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		} else if (path.equals("/profile-edit")) {
			req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
		}
		
	}
}
