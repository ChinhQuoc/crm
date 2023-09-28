package authen;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class AuthenFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if (!req.getServletPath().startsWith("/login")) {
			if (req.getSession().getAttribute("LOGIN_USER") != null) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			chain.doFilter(request, response);
		}
	}
}
