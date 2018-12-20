package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.AccountEntry;

@WebServlet("/DoiMatKhauThiSinh")
public class DoiMatKhauThiSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public DoiMatKhauThiSinh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean wrongPassword = (Boolean)getServletContext().getAttribute("wrongPassword");
		if (wrongPassword != null) {
			getServletContext().removeAttribute("wrongPassword");
		}
		else {
			wrongPassword = false;
		}
		request.setAttribute("wrongPassword", wrongPassword);
		
		Boolean changePasswordError = (Boolean)getServletContext().getAttribute("changePasswordError");
		if (changePasswordError != null) {
			getServletContext().removeAttribute("changePasswordError");
		}
		else {
			changePasswordError = false;
		}
		request.setAttribute("changePasswordError", changePasswordError);
    	
    	request.getRequestDispatcher("/WEB-INF/DoiMatKhauThiSinh.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountEntry account = (AccountEntry)request.getSession().getAttribute("account");
		
		AccountEntry acc = new AccountEntry(
				account.getUsername(),
				request.getParameter("old-password"));
		
		boolean wrongPassword = AccountDAO.checkPassword(acc);
		if (wrongPassword == true) {
			getServletContext().setAttribute("wrongPassword", wrongPassword);
			response.sendRedirect(request.getContextPath() + "/DoiMatKhauThiSinh");
			return;
		}
		
		acc.setPassword(request.getParameter("new-password"));
		boolean changePasswordError = AccountDAO.changePassword(acc);
		
		if (changePasswordError == true) {
			getServletContext().setAttribute("changePasswordError", changePasswordError);
			response.sendRedirect(request.getContextPath() + "/DoiMatKhauThiSinh");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/ThongTinThiSinh");
	}

}
