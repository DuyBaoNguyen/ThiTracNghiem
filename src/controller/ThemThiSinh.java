package controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDAO;
import model.CandidateEntry;

@WebServlet("/ThemThiSinh")
public class ThemThiSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemThiSinh() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/ThemThiSinh.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cddName = request.getParameter("cddName");
		boolean cddSex = true;
		if (request.getParameter("cddSex").equals("female")) {
			cddSex = false;
		}
		String cddAddress = request.getParameter("cddAddress");
		Date cddBirthday = null;
		
		try {
			cddBirthday = Date.valueOf(request.getParameter("cddBirthday"));
		} catch (Exception e) {
			
		}
		String cddPhone = request.getParameter("cddPhone");

		CandidateEntry cdd = new CandidateEntry(cddName, cddSex, cddAddress, cddBirthday, cddPhone);

		boolean cddError = CandidateDAO.insertCandidate(cdd);
		getServletContext().setAttribute("cddError", cddError);

		response.sendRedirect(request.getContextPath() + "/DanhSachThiSinh");
	}
}
