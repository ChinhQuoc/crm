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
import service.GroupWorkService;

@WebServlet(name = "apiGroupworkController", urlPatterns = {"/api/groupwork/delete"})
public class ApiGroupworkController extends HttpServlet{
	private GroupWorkService groupWorkService = new GroupWorkService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		if (path.equals("/api/groupwork/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = groupWorkService.deleteProject(id);
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
			response.setData(isSuccess);
			
			String dataJson = gson.toJson(response);
			
			// trả dữ liệu dạng JSON
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			out.print(dataJson);
		}
	}
}
