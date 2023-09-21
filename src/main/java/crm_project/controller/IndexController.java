package crm_project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

@WebServlet(name="indexController", urlPatterns = {"/"})
public class IndexController extends HttpServlet {

	@Override public void init() throws ServletException {
		// TODO Auto-generated method stub 
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("account");
		System.out.println(user);

		if (user != null) {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} else {
			String path = req.getRequestURL().toString();
			resp.sendRedirect(path + "login");
		}

	}

}
