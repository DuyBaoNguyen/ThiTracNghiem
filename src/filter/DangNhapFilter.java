package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.KiemTraVaiTro;
import model.AccountEntry;

@WebFilter("/*")
public class DangNhapFilter implements Filter {
	static int count = 0;

	public DangNhapFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		AccountEntry acc = null;
		String uri = ((HttpServletRequest)request).getRequestURI();
		if ( uri.indexOf("/css") > 0 || uri.indexOf("/images") > 0) {
	        chain.doFilter(request, response);
	        return;
	    }
		
		if (req.getServletPath().equals("/DangNhap") || req.getServletPath().equals("/KhongTheTruyCap")) {
			chain.doFilter(request, response);
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			res.setDateHeader("Expires", 0);
			return;
		}

		if (session != null) {
			acc = (AccountEntry) session.getAttribute("account");
		}
		if (acc == null) {
			res.sendRedirect(req.getContextPath() + "/DangNhap");
			return;
		}

		if (!KiemTraVaiTro.kiemTraVaiTro(acc.getUser().getRole().getName(), req.getServletPath())) {
			res.sendRedirect(req.getContextPath() + "/KhongTheTruyCap");
			//session.removeAttribute("account");
			return;
		}
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		res.setDateHeader("Expires", 0);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
