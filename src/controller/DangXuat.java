package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountEntry;

@WebServlet("/DangXuat")
public class DangXuat extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public DangXuat() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountEntry acc = (AccountEntry)session.getAttribute("account");
		if (acc != null) {
			session.removeAttribute("account");
		}
		response.sendRedirect(request.getContextPath() + "/DangNhap");
	}
}
