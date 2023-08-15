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
import service.UserService;

@WebServlet(name = "userController", urlPatterns = { "/user-add" })
public class UserController extends HttpServlet {
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> list = new ArrayList<Role>();
		list = roleService.getAllRole();

		req.setAttribute("listRole", list);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		int idRole = Integer.parseInt(req.getParameter("role"));
		
		boolean isSuccess = userService.addUser(fullname, password, phone, email, idRole);
		
		if (isSuccess) {
			System.out.println("Thêm thành công");
		} else {
			System.out.println("Thêm thất bại");
		}
		
		List<Role> list = new ArrayList<Role>();
		list = roleService.getAllRole();
		
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		
		
		// lấy tham số từ thẻ form tuyền qua khi người dùng click btn submit

		// tạo câu truy vấn và truyền giá trị
		/*
		 * String query = "INSERT INTO Users (fullName, email, pwd, phone, id_role)\r\n"
		 * + "VALUES ('" + fullname + "', '" + email + "', '" + password + "', '" +
		 * phone + "', "+ idRole +")";
		 */

		// Mở kết nối tới CSDL
		//Connection connection = MySqlConfig.getConnection();

		//try {
			// Truyền câu query vào db đẫ được kết nối
			//PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, fullname);
//			statement.setString(2, email);
//			statement.setString(3, password);
//			statement.setString(4, phone);

			//int count = statement.executeUpdate(query);

			//if (count > 0) {
				// insert dữ liệu thành công
				//System.out.println("Thêm thành công");
			//} else {
				// insert dữ liệu thất bại
				//System.out.println("Thêm thất bại");
			//}
		//} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("Lỗi thêm dữ liệu User " + e.getLocalizedMessage());
		//} finally {
		/*
		 * try { connection.close(); } catch (SQLException e) { // TODO Auto-generated
		 * catch block System.out.println("Lỗi đóng kết nối " +
		 * e.getLocalizedMessage()); } }
		 * 
		 * List<Role> list = new ArrayList<Role>();
		 * 
		 * try { list = getAllRole(); } catch (SQLException e) { // TODO Auto-generated
		 * catch block System.out.println("Lỗi get all role " +
		 * e.getLocalizedMessage()); }
		 */
	}

	private List<Role> getAllRole() throws SQLException {
		String query = "SELECT * FROM Role";
		Connection connection = MySqlConfig.getConnection();

		PreparedStatement statement = connection.prepareStatement(query);

		// thực thi câu truy vấn và được 1 list dữ liệu
		ResultSet resultSet = statement.executeQuery(query);
		List<Role> listRole = new ArrayList<Role>();

		// Duyệt qua từng dòng dữ liệu
		while (resultSet.next()) {
			Role role = new Role();
			role.setId(resultSet.getInt("id"));
			role.setName(resultSet.getString("name"));
			role.setDescription(resultSet.getString("description"));
			
			listRole.add(role);
		}

		return listRole;
	}
}
