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

@WebServlet(name = "roleController", urlPatterns = { "/role-add", "/role" })
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
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roleName = req.getParameter("role-name");
		String desc = req.getParameter("desc");
		
		boolean isSuccess = roleService.addRole(roleName, desc);
		
		req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		
		// Nhận tham số nếu có
		
		
		// cb câu query 
		//String query = "INSERT INTO Role(name, description) VALUES('" + roleName +"', '" + desc +"')";
		
		// Mở kết nối tới CSDL
		//Connection connection = MySqlConfig.getConnection();
		//boolean isSuccess = false;
		
		/*
		 * try { PreparedStatement statement = connection.prepareStatement(query);
		 * 
		 * int count = statement.executeUpdate();
		 * 
		 * if (count > 0) { isSuccess = true;
		 * System.out.println("Thêm role thành công"); } else {
		 * System.out.println("Thêm role thất bại"); } } catch (SQLException e) { //
		 * TODO: handle exception e.printStackTrace(); } finally { try {
		 * connection.close(); } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } }
		 */
	}
	
	private List<Role> getAllRole() throws SQLException {
		String query = "SELECT * FROM Role";
		Connection connection = MySqlConfig.getConnection();
		
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery(query);
		
		List<Role> roles = new ArrayList<Role>();
		
		while (resultSet.next()) {
			Role role = new Role();
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
			role.setDescription(resultSet.getString("description"));
			
			roles.add(role);
		}
		
		return roles;
	}
}
