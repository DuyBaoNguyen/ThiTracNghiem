package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDAO;
import model.AccountEntry;
import model.CandidateEntry;
import model.ClassEntry;

@WebServlet("/DanhSachLopHocThiSinh")
public class DanhSachLopHocThiSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DanhSachLopHocThiSinh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountEntry account = (AccountEntry)request.getSession().getAttribute("account");
		CandidateEntry cdd = new CandidateEntry(account.getUser().getId());
		List<ClassEntry> classes = ClassDAO.getClassesByCandidate(cdd);
		
		String searchStr = request.getParameter("search");
		classes = ClassDAO.searchClasses(classes, searchStr);
		
		request.setAttribute("classes", classes);
		
		request.getRequestDispatcher("/WEB-INF/DanhSachLopHocThiSinh.jsp").forward(request, response);
	}
}
