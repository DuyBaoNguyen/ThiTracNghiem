package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDAO;
import dao.ClassDAO;
import model.CandidateEntry;
import model.ClassEntry;

@WebServlet("/DanhSachLopHoc/LopHoc")
public class LopHoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public LopHoc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("classId");
		String searchStr = request.getParameter("search");
		String add = request.getParameter("add");
		
		ClassEntry classEntry = ClassDAO.getClass(new ClassEntry(classId));
		request.setAttribute("classEntry", classEntry);
		
		List<CandidateEntry> cddsInClass = CandidateDAO.getCandidateInClass(classEntry);
		cddsInClass = CandidateDAO.searchCandidates(cddsInClass, searchStr);
		request.setAttribute("cddsInClass", cddsInClass);
		
		if (add != null && add.length() > 0) {
			List<CandidateEntry> cddsOutClass = CandidateDAO.getCandidateOutClass(classEntry);
			cddsOutClass = CandidateDAO.searchCandidates(cddsOutClass, add);
			request.setAttribute("cddsOutClass", cddsOutClass);
		}
		
		
		Boolean cddInClassError = (Boolean)getServletContext().getAttribute("cddToClassError");
		if (cddInClassError != null) {
			getServletContext().removeAttribute("cddInClassError");
		}
		else {
			cddInClassError = false;
		}
		request.setAttribute("cddInClassError", cddInClassError);
		
		request.getRequestDispatcher("/WEB-INF/LopHoc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
