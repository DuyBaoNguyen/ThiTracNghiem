package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.UserEntry;

@WebServlet("/XoaNguoiDung")
public class XoaNguoiDung extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public XoaNguoiDung() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		boolean userError = UserDAO.deleteUser(new UserEntry(userId));
		getServletContext().setAttribute("userError", userError);
		response.sendRedirect(request.getContextPath() + "/DanhSachNguoiDung");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
