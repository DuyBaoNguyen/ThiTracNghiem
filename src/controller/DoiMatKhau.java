package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.AccountEntry;

@WebServlet("/DoiMatKhau")
public class DoiMatKhau extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DoiMatKhau() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		AccountEntry acc = new AccountEntry(username);
		request.setAttribute("acc", acc);
		request.getRequestDispatcher("/WEB-INF/DoiMatKhau.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean accError = AccountDAO.changePassword(new AccountEntry(username, password));
		getServletContext().setAttribute("accError", accError);
		response.sendRedirect(request.getContextPath() + "/DanhSachTaiKhoan");
	}
}
