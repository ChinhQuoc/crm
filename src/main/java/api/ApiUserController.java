package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.User;
import service.UserService;

@WebServlet(name = "apiUserController", urlPatterns = {"/api/user", "/api/user/delete"})
public class ApiUserController extends HttpServlet{
	private UserService userService = new UserService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println("PATH: " + path);
		
		if (path.equals("/api/user")) {
			List<User> listUser = userService.getAllUser();
			
			// chuyển list or mảng về JSON
			String dataJson = gson.toJson(listUser);
			
			// trả dữ liệu dạng JSON
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			out.print(dataJson);
			
			out.flush();
		} else if (path.equals("/api/user/delete")) {
			String param = req.getQueryString();
			String idUser = param.split("=")[1];
			if (idUser != null) {
				String result = userService.deleteUser(idUser);
				
				String dataJson = gson.toJson(result);
				
				// trả dữ liệu dạng JSON
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				
				out.print(dataJson);
				
				out.flush();
			}
		}
	}
}
