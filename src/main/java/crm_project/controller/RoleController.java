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
import service.RoleService;

@WebServlet(name = "roleController", urlPatterns = { "/role-add", "/role", "/role-edit" })
public class RoleController extends HttpServlet{
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy ra đường dẫn mà người dùng đang gọi
		String path = req.getServletPath();
		// kiểm tra đường dẫn người dùng đang gọi là đường dẫn nào để hiển thị giao diện tương ứng
		if (path.equals("/role-add")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		} else if (path.equals("/role")) {
			List<Role> roles = new ArrayList<Role>();
			roles = roleService.getAllRole();
			
			req.setAttribute("listRole", roles);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		} else if (path.equals("/role-edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			
			setRole(req, id);
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		String roleName = req.getParameter("role-name");
		String desc = req.getParameter("desc");
		
		if (path.equals("/role-edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Role role = new Role(id, roleName, desc);
			
			boolean isSuccess = roleService.editRole(role);
			req.setAttribute("isSuccess", isSuccess);
			
			// get info role after edited
			setRole(req, id);
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
		} else if (path.equals("/role-add")) {
			boolean isSuccess = roleService.addRole(roleName, desc);
			
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		}
	}
	
	private void setRole(HttpServletRequest req, int id) {
		Role role = new Role();
		role = roleService.findRoleById(id);
		req.setAttribute("role", role);
	}
}
