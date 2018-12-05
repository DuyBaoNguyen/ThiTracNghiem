package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoleDAO;
import dao.UserDAO;
import model.RoleEntry;
import model.UserEntry;

@WebServlet("/ThemNguoiDung")
public class ThemNguoiDung extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ThemNguoiDung() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoleEntry> roles = RoleDAO.getRoles();
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/WEB-INF/ThemNguoiDung.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		boolean userSex = true;
		if (request.getParameter("userSex").equals("female")) {
			userSex = false;
		}
		String userAddress = request.getParameter("userAddress");
		Date userBirthday = null;
		try {
			userBirthday = Date.valueOf(request.getParameter("userBirthday"));
		} catch (Exception e) {
			
		}
		String userPhone = request.getParameter("userPhone");
		RoleEntry role = new RoleEntry(Integer.parseInt(request.getParameter("roleId")));

		UserEntry user = new UserEntry(userName, userSex, userAddress, userBirthday, userPhone, role);

		boolean userError = UserDAO.insertUser(user);
		getServletContext().setAttribute("userError", userError);

		response.sendRedirect(request.getContextPath() + "/DanhSachNguoiDung");
	}

}
