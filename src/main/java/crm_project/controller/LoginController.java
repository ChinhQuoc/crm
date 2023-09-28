package crm_project.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.MySqlConfig;
import entity.User;
import service.AuthenService;

/**
 * package Controller: là nơi chứa toàn bộ file lq tới khai báo đường dẫn
 * và xử lý đường dẫn
 *
 */

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	private AuthenService authenService = new AuthenService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		List<User> users = authenService.login(email, password);
		
		if (users.size() > 0) {
			HttpSession session = req.getSession();
			// user tồn tại, login thành công
			session.setAttribute("LOGIN_USER", users.get(0));
			
			String path = req.getRequestURL().toString();
			resp.sendRedirect(path.replaceAll("/login", ""));
		} else {
			req.setAttribute("isSuccess", false);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
}
