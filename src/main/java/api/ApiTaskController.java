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
import service.JobService;

@WebServlet(name = "apiTaskController", urlPatterns = {"/api/task/delete"})
public class ApiTaskController extends HttpServlet{
	private JobService jobService = new JobService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int idStatus = Integer.parseInt(req.getParameter("idStatus"));
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		boolean isSuccess = jobService.deleteJob(id, idStatus, idUser);
		
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
