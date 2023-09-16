package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MySqlConfig;
import entity.Role;
import entity.User;

public class UserRepository {
	public List<User> findAll() {
		String query = "SELECT u.id, u.email, u.lastName, u.firstName, u.userName, r.name  FROM Users u JOIN Role r ON u.id_role = r.id";
		Connection connection = MySqlConfig.getConnection();
		List<User> users = new ArrayList<User>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setUserName(result.getString("userName"));
				
				Role role = new Role();
				role.setName(result.getString("name"));
				user.setRole(role);
				
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy danh sách user" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return users;
	}
	
	public int insert(String fullname, String password, String phone, String email, int idRole) {
		String query = "INSERT INTO Users (fullName, email, pwd, phone, id_role)\r\n" + "VALUES ('" + fullname + "', '"
				+ email + "', '" + password + "', '" + phone + "', "+ idRole +")";
		Connection connection = MySqlConfig.getConnection();
		int count = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thêm user " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM Users where id = " + id;
		Connection connection = MySqlConfig.getConnection();
		int count = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi xóa user " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public List<User> findNameUsers() {
		String query = "SELECT id, fullName FROM Users";
		Connection connection = MySqlConfig.getConnection();
		List<User> users = new ArrayList<User>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setFullName(result.getString("fullName"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy danh sách user" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return users;
	}
	
	public User findById(int id) {
		String query = "SELECT id, email, fullName, image FROM Users WHERE id = "+ id;
		Connection conn = MySqlConfig.getConnection();
		User user = new User();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				user.setId(result.getInt("id"));
				user.setEmail(result.getString("email"));
				user.setFullName(result.getString("fullName"));
				user.setImage(result.getString("image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy thông tin user " + e.getLocalizedMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}
}
