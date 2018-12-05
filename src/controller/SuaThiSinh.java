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

@WebServlet("/SuaThiSinh")
public class SuaThiSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuaThiSinh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cddId = request.getParameter("cddId");
		CandidateEntry cdd = CandidateDAO.getCandidate(new CandidateEntry(cddId));
		request.setAttribute("cdd", cdd);
		request.getRequestDispatcher("/WEB-INF/SuaThiSinh.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cddId = request.getParameter("cddId");
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

		CandidateEntry cdd = new CandidateEntry(cddId, cddName, cddSex, cddAddress, cddBirthday, cddPhone);

		boolean cddError = CandidateDAO.updateCandidate(cdd);
		getServletContext().setAttribute("cddError", cddError);
		response.sendRedirect(request.getContextPath() + "/DanhSachThiSinh");
	}

}
