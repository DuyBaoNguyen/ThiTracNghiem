package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDAO;
import dao.ClassDAO;
import model.ClassEntry;
import model.ExamEntry;

@WebServlet("/DanhSachDeThi/DeThi")
public class DeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public DeThi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int examId = Integer.parseInt(request.getParameter("examId"));
		String searchStr = request.getParameter("search");
		String add = request.getParameter("add");
		
		ExamEntry exam = ExamDAO.getExamWithName(new ExamEntry(examId));
		request.setAttribute("exam", exam);
		
		List<ClassEntry> classesHaveExam = ClassDAO.getClassesHaveExam(exam);
		classesHaveExam = ClassDAO.searchClasses(classesHaveExam, searchStr);
		request.setAttribute("classesHaveExam", classesHaveExam);
		
		if (add != null && add.length() > 0) {
			List<ClassEntry> classesNoneExam = ClassDAO.getClassesNoneExam(exam);
			classesNoneExam = ClassDAO.searchClasses(classesNoneExam, add);
			request.setAttribute("classesNoneExam", classesNoneExam);
		}
		
		Boolean classExamError = (Boolean)getServletContext().getAttribute("classExamError");
		if (classExamError != null) {
			getServletContext().removeAttribute("classExamError");
		}
		else {
			classExamError = false;
		}
		request.setAttribute("classExamError", classExamError);
		
		request.getRequestDispatcher("/WEB-INF/DeThi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
