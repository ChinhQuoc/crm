package repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.MySqlConfig;
import entity.Job;

public class TaskRepository {
	public List<Job> findAll() {
		String query = "SELECT j.id, j.name, j.startDate, j.endDate, p.name as nameProject, s.name as status, u.fullName as username FROM Job j"
				+ "	JOIN Job_Status_Users jsu ON j.id = jsu.id_job" + "	JOIN Project p ON j.id_project = p.id"
				+ "	JOIN Status s ON s.id = jsu.id_status" + " JOIN Users u ON u.id = jsu.id_user";
		Connection connection = MySqlConfig.getConnection();
		List<Job> listJob = new ArrayList<Job>();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Job job = new Job();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getString("name"));
				job.setStartDate(resultSet.getDate("startDate"));
				job.setEndDate(resultSet.getDate("endDate"));
				job.setNameProject(resultSet.getString("nameProject"));
				job.setStatus(resultSet.getString("status"));
				job.setNameUser(resultSet.getString("username"));

				listJob.add(job);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy danh sách công việc " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return listJob;

	}

	public int insert(String name, String content, String startDate, String endDate, int idProject, int idUser) {
		String query = "{call sp_createJob('" + name + "', '" + content + "', '" + startDate + "', '" + endDate + "', "
				+ idProject + ", " + idUser + ")}";
		Connection conn = MySqlConfig.getConnection();
		int count = 0;

		try {
			CallableStatement statement = conn.prepareCall(query);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi tạo job " + e.getLocalizedMessage());
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

	public Job findById(int id) {
		String query = "SELECT j.id, j.name, j.content, j.startDate, j.endDate, p.name as nameProject, p.id as idProject, u.fullName as username, u.id as idUser FROM Job j"
				+ "	JOIN Job_Status_Users jsu ON j.id = jsu.id_job" + "	JOIN Project p ON j.id_project = p.id"
				+ " JOIN Users u ON u.id = jsu.id_user WHERE j.id = " + id;
		Connection conn = MySqlConfig.getConnection();
		Job job = new Job();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				job.setContent(result.getString("content"));
				job.setStartDate(result.getDate("startDate"));
				job.setEndDate(result.getDate("endDate"));
				job.setNameProject(result.getString("nameProject"));
				job.setIdProject(result.getInt("idProject"));
				job.setNameUser(result.getString("username"));
				job.setIdUser(result.getInt("idUser"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy thông tin job " + e.getLocalizedMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return job;
	}

	public int update(int id, String name, String content, String startDate, String endDate, int idProject, int idUser,
			int exIdProject, int exIdUser) {
		String query = "{call sp_updateJob(" + id + ", '" + name + "', '" + content + "', '" + startDate + "', '"
				+ endDate + "', " + idProject + ", " + idUser + ", " + exIdProject + ", " + exIdUser + ")}";
		Connection conn = MySqlConfig.getConnection();
		int count = 0;

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			count = statement.executeUpdate();
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi cập nhật job " + e.getLocalizedMessage());
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

	public int deleteById(int id, int idProject, int idUser) {
		String query = "{call sp_updateJob(" + id + ", " + idProject + ", " + idUser + ")}";
		Connection conn = MySqlConfig.getConnection();
		int count = 0;

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			count = statement.executeUpdate();
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi xóa job " + e.getLocalizedMessage());
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

	public List<Job> selectByIdUser(int id) {
		String query = "SELECT j.id, j.name, p.id AS idProject, p.name AS nameProject, j.startDate, j.endDate, s.id AS idStatus, s.name AS status FROM Job j"
				+ "	JOIN Job_Status_Users jsu ON jsu.id_user ="+ id +" AND j.id = jsu.id_job"
				+ "	JOIN Project p ON p.id = j.id_project" + "	JOIN Status s ON s.id = jsu.id_status";
		Connection conn = MySqlConfig.getConnection();
		List<Job> jobs = new ArrayList<>();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Job job = new Job();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getString("name"));
				job.setStartDate(resultSet.getDate("startDate"));
				job.setEndDate(resultSet.getDate("endDate"));
				job.setNameProject(resultSet.getString("nameProject"));
				job.setStatus(resultSet.getString("status"));

				jobs.add(job);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi lấy danh sách job " + e.getLocalizedMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return jobs;
	}
}
