package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.UserEntry;

@WebServlet("/DanhSachNguoiDung")
public class DanhSachNguoiDung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DanhSachNguoiDung() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchStr = request.getParameter("search");
		List<UserEntry> users = UserDAO.getUsers();
		users = UserDAO.searchUsers(users, searchStr);
		request.setAttribute("users", users);
		
		Boolean userError = (Boolean)getServletContext().getAttribute("userError");
		if (userError != null) {
			getServletContext().removeAttribute("userError");
		}
		else {
			userError = false;
		}
		request.setAttribute("userError", userError);
		request.getRequestDispatcher("/WEB-INF/DanhSachNguoiDung.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
