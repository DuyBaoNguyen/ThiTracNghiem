package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.AccountEntry;
import model.UserEntry;

@WebServlet("/ThongTinNguoiDung")
public class ThongTinNguoiDung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThongTinNguoiDung() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountEntry account = (AccountEntry)request.getSession().getAttribute("account");
		UserEntry user = new UserEntry(account.getUser().getId());
		UserDAO.getUser(user);
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/WEB-INF/ThongTinNguoiDung.jsp").forward(request, response);
	}
}
