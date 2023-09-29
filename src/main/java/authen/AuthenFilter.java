package authen;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Job;
import entity.User;
import service.UserService;

@WebFilter(urlPatterns = "/*")
public class AuthenFilter implements Filter {
	private UserService userService = new UserService();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String path = req.getServletPath();

		if (!req.getServletPath().startsWith("/login")) {
			if (req.getSession().getAttribute("LOGIN_USER") != null) {
				HttpSession session = req.getSession();
				User currentUser = (User) session.getAttribute("LOGIN_USER");
				int idUser = currentUser.getId();
				User user = userService.getUserById(idUser);

				if (isAuthorized(path, user.getRoleName())) {
					chain.doFilter(request, response);
				} else {
					resp.sendRedirect(req.getContextPath() + "/error-permission");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isAuthorized(String path, String role) {
		boolean canAccess = true;
		switch (role) {
		case "LEADER": {
			canAccess = !path.equals("/api/groupwork/delete") && !path.equals("/role") && !path.equals("/role-add")
					&& !path.equals("/role-edit") && !path.equals("/api/role/delete") && !path.equals("/profile-edit");
			break;
		}
		case "MEMBER": {
			canAccess = !path.equals("/users") && !path.equals("/user-add") && !path.equals("/user-detail")
					&& !path.equals("/user-edit") && !path.equals("/api/user/delete") && !path.equals("/groupwork")
					&& !path.equals("/groupwork-detail") && !path.equals("/groupwork-add")
					&& !path.equals("/groupwork-edit") && !path.equals("/api/groupwork/delete") && !path.equals("/tasks")
					&& !path.equals("/task-add") && !path.equals("/task-edit") && !path.equals("/api/task/delete")
					&& !path.equals("/role") && !path.equals("/role-add") && !path.equals("/role-edit")
					&& !path.equals("/api/role/delete");
			break;
		}
		default:
			canAccess = true;
		}

		return canAccess;
	}
}
