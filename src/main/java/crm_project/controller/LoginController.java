package crm_project.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySqlConfig;
import entity.User;

/**
 * package Controller: là nơi chứa toàn bộ file lq tới khai báo đường dẫn
 * và xử lý đường dẫn
 *
 */

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// giây: 8 * 60 * 60
		int maxAge = 8 * 60 * 60;
		
		// URLEncoder.encode: fix lỗi invalid character
		Cookie cookie = new Cookie("hoten", URLEncoder.encode("Nguyễn Văn A", "UTF-8"));
		cookie.setMaxAge(maxAge);
		
		// yêu cầu client tạo cookie
		resp.addCookie(cookie);
		
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// ?: đại diện cho tham số sẽ đc truyền vào khi sử dụng JDBC
		String query = "SELECT * FROM Users u WHERE u.email = '" + email + "' AND u.pwd = '" + password + "'";
		
		// Mở kết nối tới CSDL
		Connection conn = MySqlConfig.getConnection();
		
		try {
			// chuẩn bị câu query cho truyền xuống CSDL thông qua PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			
			// gán gtri cho tham số trong câu query có dấu (?): truyền tham số cho câu query
//			statement.setString(1, email);
//			statement.setString(2, password);
			
			// thực thi câu query và lấy kết quả
			// executeQuery: Nếu như câu query là câu SELECT
			// executeUpdate: Nếu như câu query khác SELECT -> INSERT, UPDATE, DELETE,...
			ResultSet resultSet = statement.executeQuery(query);
			List<User> listUser = new ArrayList<User>();
			
			// Khi nào resultSet mà còn qua dòng tiếp theo được thì làm
			while(resultSet.next()) {
				// duyệt qua từng dòng dữ liệu query được trong DB
				User user = new User();
				
				// lấy dữ liệu từ cột duyệt qua đc và lưu vào thuộc tính của đối tượng user
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("username"));
				
				listUser.add(user);
			}
			
			if (listUser.size() > 0) {
				// user tồn tại, login thành công
				System.out.println("Đăng nhập thành công");
			} else {
				// user K tồn tại, login thất bại
				System.out.println("Đăng nhập thất bại");
			}
		} catch (Exception e) {
			System.out.println("Lỗi thực thi truy vấn " + e.getLocalizedMessage());
		}
		
	}
}
