package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MySqlConfig;
import entity.User;

public class AuthenRepository {
	public List<User> login(String email, String password) {
		String query = "SELECT * FROM Users u WHERE u.email = '" + email + "' AND u.pwd = '" + password + "'";
		Connection conn = MySqlConfig.getConnection();
		List<User> listUser = new ArrayList<User>();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("username"));
				
				listUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi login " + e.getLocalizedMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return listUser;
	}
}
