package crm_project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;

@WebServlet(name = "profileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet{
	private UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		
		user = userService.getUserById(1);
		
		req.setAttribute("user", user);
		req.getRequestDispatcher("profile.jsp").forward(req, resp);
	}
}
