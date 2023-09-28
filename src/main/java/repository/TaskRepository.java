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
		String query = "SELECT j.id, j.name, j.startDate, j.endDate, p.name as nameProject, s.name as status, jsu.id_status as idStatus, u.id as idUser, u.fullName as username FROM Job j"
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
				job.setIdStatus(resultSet.getInt("idStatus"));
				job.setIdUser(resultSet.getInt("idUser"));

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

	public boolean insert(String name, String content, String startDate, String endDate, int idProject, int idUser) {
		boolean isSuccess = false;
		String query = "{call sp_createJob('" + name + "', '" + content + "', '" + startDate + "', '" + endDate + "', "
				+ idProject + ", " + idUser + ")}";
		Connection conn = MySqlConfig.getConnection();

		try {
			CallableStatement statement = conn.prepareCall(query);
			isSuccess = statement.execute();
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

		return isSuccess;
	}

	public Job findById(int id) {
		String query = "SELECT j.id, j.name, j.content, j.startDate, j.endDate, p.name as nameProject, p.id as idProject, u.fullName as username, u.id as idUser, jsu.id_status as idStatus FROM Job j"
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
				job.setIdStatus(result.getInt("idStatus"));
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

	public boolean update(int id, String name, String content, String startDate, String endDate, int idProject, int idUser,
			int exIdProject, int exIdUser) {
		boolean isSuccess = false;
		String query = "{call sp_updateJob(" + id + ", '" + name + "', '" + content + "', '" + startDate + "', '"
				+ endDate + "', " + idProject + ", " + idUser + ", " + exIdProject + ", " + exIdUser + ")}";
		Connection conn = MySqlConfig.getConnection();

		try {
			PreparedStatement statement = conn.prepareCall(query);
			isSuccess = statement.execute();
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

		return isSuccess;
	}

	public boolean deleteById(int id, int idStatus, int idUser) {
		String query = "{call sp_deleteJob(" + id + ", " + idStatus + ", "+ idUser + ")}";
		Connection conn = MySqlConfig.getConnection();
		boolean isSuccess = false;

		try {
			PreparedStatement statement = conn.prepareCall(query);
			isSuccess = statement.execute();
			System.out.println(isSuccess);
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

		return isSuccess;
	}

	public List<Job> selectByIdUser(int id) {
		String query = "SELECT j.id, j.name, p.id AS idProject, p.name AS nameProject, j.startDate, j.endDate, s.id AS idStatus, s.name AS status FROM Job j"
				+ "	JOIN Job_Status_Users jsu ON jsu.id_user =" + id + " AND j.id = jsu.id_job"
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
				job.setIdStatus(resultSet.getInt("idStatus"));

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

	public List<Job> findByIdUserAndIdStatus(int idUser, int idStatus) {
		String query = "SELECT * FROM Job j JOIN Job_Status_Users jsu ON j.id = jsu.id_job AND jsu.id_status = "
				+ idStatus + " AND jsu.id_user = " + idUser;
		Connection conn = MySqlConfig.getConnection();
		List<Job> jobs = new ArrayList<>();
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				Job job = new Job();
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				job.setStartDate(result.getDate("startDate"));
				job.setEndDate(result.getDate("endDate"));
				job.setContent(result.getString("content"));
				
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
