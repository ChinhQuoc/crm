package crm_project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySqlConfig;
import entity.Job;
import entity.Role;
import entity.User;
import service.JobService;
import service.RoleService;
import service.UserService;

@WebServlet(name = "userController", urlPatterns = { "/user-add", "/users", "/user-detail", "/user-edit" })
public class UserController extends HttpServlet {
	private final int HASNT_STARTED = 1;
	private final int STARTING = 2;
	private final int STARTED = 3;

	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private JobService jobService = new JobService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		if (path.equals("/user-add")) {
			setListRole(req);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else if (path.equals("/users")) {
			List<User> listUser = new ArrayList<User>();
			listUser = userService.getAllUser();
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else if (path.equals("/user-detail")) {
			int idUser = Integer.parseInt(req.getParameter("id"));

			setUser(req, idUser);

			int totalHasntStatedJobs = setJob(req, idUser, HASNT_STARTED, "hasntStatedJobs").size();
			int totalStartingJobs = setJob(req, idUser, STARTING, "startingJobs").size();
			int totalStatedJobs = setJob(req, idUser, STARTED, "startedJobs").size();

			int totalJobs = totalHasntStatedJobs + totalStartingJobs + totalStatedJobs;
			setPercentForEachStatus(req, totalJobs, totalHasntStatedJobs, totalStartingJobs, totalStatedJobs);
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
		} else if (path.equals("/user-edit")) {
			int id = Integer.parseInt(req.getParameter("id"));

			setUser(req, id);
			setListRole(req);
			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String userName = req.getParameter("userName");
		int idRole = Integer.parseInt(req.getParameter("role"));

		String path = req.getServletPath();

		if (path.equals("/user-add")) {
			boolean isSuccess = userService.addUser(firstName, lastName, userName, fullname, password, phone, email,
					idRole);
			req.setAttribute("isSuccess", isSuccess);

			setListRole(req);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else if (path.equals("/user-edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = userService.updateUser(id, firstName, lastName, fullname, userName, phone, idRole);
			req.setAttribute("isSuccess", isSuccess);

			setUser(req, id);
			setListRole(req);
			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
		}
	}

	private void setListRole(HttpServletRequest req) {
		List<Role> list = new ArrayList<Role>();
		list = roleService.getAllRole();
		req.setAttribute("listRole", list);
	}

	private void setUser(HttpServletRequest req, int id) {
		User user = new User();
		user = userService.getUserById(id);
		req.setAttribute("user", user);
	}

	private List<Job> setJob(HttpServletRequest req, int idUser, int idStatus, String attribute) {
		List<Job> jobs = new ArrayList<>();
		jobs = jobService.getByIdUserAndIdStatus(idUser, idStatus);
		req.setAttribute(attribute, jobs);
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
