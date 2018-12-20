package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.AccountEntry;

@WebServlet("/DanhSachTaiKhoan")
public class DanhSachTaiKhoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public DanhSachTaiKhoan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchStr = request.getParameter("search");
		
		List<AccountEntry> accs = AccountDAO.getAccounts();
		accs = AccountDAO.searchClasses(accs, searchStr);
		request.setAttribute("accs", accs);
		
		Boolean accError = (Boolean)getServletContext().getAttribute("accError");
		if (accError != null) {
			getServletContext().removeAttribute("accError");
		}
		else {
			accError = false;
		}
		request.setAttribute("accError", accError);
		
		request.getRequestDispatcher("/WEB-INF/DanhSachTaiKhoan.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
