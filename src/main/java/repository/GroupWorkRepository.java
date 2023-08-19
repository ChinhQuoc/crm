package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MySqlConfig;
import entity.Project;

public class GroupWorkRepository {
	public List<Project> findAll() {
		String query = "SELECT * FROM Project";
		Connection connection = MySqlConfig.getConnection();
		List<Project> projects = new ArrayList<Project>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				Project project = new Project();
				project.setName(result.getString("name"));
				project.setStartDate(result.getDate("startDate"));
				project.setEndDate(result.getDate("endDate"));
				
				projects.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return projects;
	}
	
	public int insert(String name, String startDate, String endDate) {
		String query = "INSERT INTO Project(name, startDate, endDate) VALUES('"+ name +"', '"+ startDate +"', '"+ endDate +"')";
		Connection connection = MySqlConfig.getConnection();
		int count = 0;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thêm project " + e.getLocalizedMessage());
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
