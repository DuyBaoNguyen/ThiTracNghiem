package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.AccountEntry;

@WebServlet("/XoaTaiKhoan")
public class XoaTaiKhoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public XoaTaiKhoan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		boolean accError = AccountDAO.deleteAccount(new AccountEntry(username));
		getServletContext().setAttribute("accError", accError);
		response.sendRedirect(request.getContextPath() + "/DanhSachTaiKhoan");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
