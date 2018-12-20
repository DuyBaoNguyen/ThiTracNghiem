package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDAO;
import model.ExamEntry;

@WebServlet("/DanhSachDeThi")
public class DanhSachDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DanhSachDeThi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchStr = request.getParameter("search");
		List<ExamEntry> exams = ExamDAO.getExams();
		exams = ExamDAO.searchExams(exams, searchStr);
		request.setAttribute("exams", exams);
		
		Boolean examError = (Boolean)getServletContext().getAttribute("examError");
		if (examError != null) {
			getServletContext().removeAttribute("examError");
		}
		else {
			examError = false;
		}
		request.setAttribute("examError", examError);
		
		request.getRequestDispatcher("/WEB-INF/DanhSachDeThi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
