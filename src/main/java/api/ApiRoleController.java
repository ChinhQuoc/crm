package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import payload.response.BaseResponse;
import service.RoleService;

@WebServlet(name = "apiRoleController", urlPatterns = {"/api/role/delete"})
public class ApiRoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	private Gson gson = new Gson();
			
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/api/role/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = roleService.deleteRole(id);
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
			response.setData(isSuccess);
			
			String dataJson = gson.toJson(response);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			out.print(dataJson);
		}
	}
}
