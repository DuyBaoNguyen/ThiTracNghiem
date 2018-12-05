package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDAO;
import model.CandidateEntry;

@WebServlet("/DanhSachThiSinh")
public class DanhSachThiSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DanhSachThiSinh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchStr = request.getParameter("search");
		List<CandidateEntry> cdds = CandidateDAO.getCandidates();
		cdds = CandidateDAO.searchCandidates(cdds, searchStr);
		request.setAttribute("cdds", cdds);
		
		Boolean cddError = (Boolean)getServletContext().getAttribute("cddError");
		if (cddError != null) {
			getServletContext().removeAttribute("cddError");
		}
		else {
			cddError = false;
		}
		request.setAttribute("cddError", cddError);
		request.getRequestDispatcher("/WEB-INF/DanhSachThiSinh.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
