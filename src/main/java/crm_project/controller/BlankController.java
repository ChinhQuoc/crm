package crm_project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.UserService;

@WebServlet(name = "blankController", urlPatterns = {"/blank"})
public class BlankController extends HttpServlet{
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("LOGIN_USER");
		
		int idUser = currentUser.getId();
		User user = userService.getUserById(idUser);
		req.setAttribute("user", user);
		req.getRequestDispatcher("blank.jsp").forward(req, resp);
	}
}
