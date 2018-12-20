package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import dao.UserDAO;
import model.AccountEntry;
import model.UserEntry;

@WebServlet("/ThemTaiKhoan")
public class ThemTaiKhoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ThemTaiKhoan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchStr = request.getParameter("search");
		List<UserEntry> users = UserDAO.getUsersNoAccount();
		users = UserDAO.searchUsers(users, searchStr);
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/ThemTaiKhoan.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean accError = AccountDAO.insertAccount(new AccountEntry(username, password));
		getServletContext().setAttribute("accError", accError);
		response.sendRedirect(request.getContextPath() + "/DanhSachTaiKhoan");
	}

}
