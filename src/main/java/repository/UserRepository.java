package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.MySqlConfig;

public class UserRepository {
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
}
