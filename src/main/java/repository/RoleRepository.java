package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MySqlConfig;
import entity.Role;

/*
 * RoleRepository: chứa toàn bộ câu truy vấn lq đến bảng Role
 */
public class RoleRepository {
	public int insert(String name, String desc) {
		String query = "INSERT INTO `Role`(name, description) VALUES('" + name + "', '" + desc + "')";
		Connection connection = MySqlConfig.getConnection();
		int count = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thêm role " + e.getLocalizedMessage());
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

	/*
	 * Đối với câu select tên hàm sẽ bắt đầu = chữ find nếu có đk Where: by
	 */
	public List<Role> findAll() {
		String query = "SELECT * FROM Role";
		Connection connection = MySqlConfig.getConnection();
		List<Role> listRole = new ArrayList<Role>();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));

				listRole.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy danh sách role " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return listRole;
	}

	public Role findById(int id) {
		String query = "SELECT * FROM Role WHERE id = " + id;
		Connection connection = MySqlConfig.getConnection();
		Role role = new Role();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				role.setId(result.getInt("id"));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy thông tin role " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return role;
	}

	public int update(Role role) {
		String query = "UPDATE Role SET name = '" + role.getName() + "', description = '" + role.getDescription()
				+ "' WHERE id = " + role.getId();
		Connection connection = MySqlConfig.getConnection();
		int count = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi cập nhật thông tin role " + e.getLocalizedMessage());
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
		String query = "DELETE FROM Role WHERE id = "+ id;
		Connection conn = MySqlConfig.getConnection();
		int count = 0;
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi xóa role " + e.getLocalizedMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
}
