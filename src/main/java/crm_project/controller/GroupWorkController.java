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
import service.GroupWorkService;

@WebServlet(name = "groupWorkController", urlPatterns = {"/groupwork-add", "/groupwork"})
public class GroupWorkController extends HttpServlet{
	private GroupWorkService groupWorkService = new GroupWorkService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/groupwork-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (path.equals("/groupwork")) {
			List<Project> projects = new ArrayList<Project>();
			
			projects = groupWorkService.getAllProject();
			
			req.setAttribute("projects", projects);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String startDate = formatDate(req.getParameter("startDate"));
		String endDate = formatDate(req.getParameter("endDate"));
		
		boolean isSuccess = groupWorkService.addProject(name, startDate, endDate);
		
		req.setAttribute("isSuccess", isSuccess);
		req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	}
	
	private String formatDate(String dateString){
		String[] date = dateString.split("/");
		String result = date[2] + '-' + date[1] + "-" + date[0]; 
		
		return result;
	}
}
