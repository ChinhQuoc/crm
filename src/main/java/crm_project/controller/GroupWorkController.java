package crm_project.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySqlConfig;
import entity.Project;

@WebServlet(name = "groupWorkController", urlPatterns = {"/groupwork-add", "/groupwork"})
public class GroupWorkController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/groupwork-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (path.equals("/groupwork")) {
			List<Project> projects = new ArrayList<Project>();
			
			try {
				projects = getAllProject();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			req.setAttribute("projects", projects);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String startDate = formatDate(req.getParameter("startDate"));
		String endDate = formatDate(req.getParameter("endDate"));
		
		String query = "INSERT INTO Project(name, startDate, endDate) VALUES('"+ name +"', '"+ startDate +"', '"+ endDate +"')";
		
		Connection connection = MySqlConfig.getConnection();
		boolean isSuccess = false;
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			int count = statement.executeUpdate();
			
			if (count > 0) {
				isSuccess = true;
			}
			
			req.setAttribute("isSuccess", isSuccess);
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
		
		req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}
	
	private List<Project> getAllProject() throws SQLException {
		String query = "SELECT * FROM Project";
		
		Connection connection = MySqlConfig.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery(query);
		
		List<Project> projects = new ArrayList<Project>();
		
		while (result.next()) {
			Project project = new Project();
			project.setName(result.getString("name"));
			project.setStartDate(result.getDate("startDate"));
			project.setEndDate(result.getDate("endDate"));
			
			projects.add(project);
		}
		
		return projects;
	}
	
	private String formatDate(String dateString){
		String[] date = dateString.split("/");
		String result = date[2] + '-' + date[1] + "-" + date[0]; 
		
		return result;
	}
}
