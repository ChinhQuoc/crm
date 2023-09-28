package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MySqlConfig;
import entity.Status;

public class StatusRepository {
	public List<Status> findAll() {
		String query = "SELECT * FROM Status";
		Connection conn = MySqlConfig.getConnection();
		List<Status> listStatus = new ArrayList<Status>();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				Status status = new Status();
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
				
				listStatus.add(status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy danh sách trạng thái " + e.getLocalizedMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return listStatus;
	}
}
