package crm_project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySqlConfig;
import entity.Role;
import entity.User;
import service.RoleService;
import service.UserService;

@WebServlet(name = "userController", urlPatterns = { "/user-add", "/users", "/user-detail" })
public class UserController extends HttpServlet {
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/user-add")) {
			List<Role> list = new ArrayList<Role>();
			list = roleService.getAllRole();

			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else if (path.equals("/users")) {
			List<User> listUser = new ArrayList<User>();
			listUser = userService.getAllUser();
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else if (path.equals("/user-detail")) {
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		int idRole = Integer.parseInt(req.getParameter("role"));
		
		boolean isSuccess = userService.addUser(fullname, password, phone, email, idRole);
		req.setAttribute("isSuccess", isSuccess);
		if (isSuccess) {
			System.out.println("Thêm thành công");
		} else {
			System.out.println("Thêm thất bại");
		}
		
		List<Role> list = new ArrayList<Role>();
		list = roleService.getAllRole();
		
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
}
