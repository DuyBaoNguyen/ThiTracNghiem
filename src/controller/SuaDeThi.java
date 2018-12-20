package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDAO;
import dao.FieldDAO;
import dao.TypeQuestionDAO;
import model.ExamEntry;
import model.ExamStructureEntry;
import model.FieldEntry;
import model.TypeQuestionEntry;

@WebServlet("/SuaDeThi")
public class SuaDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuaDeThi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int examId = Integer.parseInt(request.getParameter("examId"));
		ExamEntry exam = ExamDAO.getExam(new ExamEntry(examId));
		request.setAttribute("exam", exam);
		
		List<FieldEntry> fields = FieldDAO.getFields();
		request.setAttribute("fields", fields);
		request.getRequestDispatcher("/WEB-INF/SuaDeThi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int examId = Integer.parseInt(request.getParameter("examId"));
		String examName = request.getParameter("examName");
		int totalTime= Integer.parseInt(request.getParameter("totalTime"));
		List<ExamStructureEntry> examStructures = new ArrayList<ExamStructureEntry>();
		int sumQuestions = 0;
		
		List<TypeQuestionEntry> typeQuestions = TypeQuestionDAO.getTypeQuestions();
		for (TypeQuestionEntry item : typeQuestions) {
			ExamStructureEntry struct = new ExamStructureEntry(item);
			String totalQuestions = request.getParameter("totalQuestion" + item.getId());
			if (totalQuestions.equals("")) {
				struct.setNumberQuestions(0);
			}
			else {
				struct.setNumberQuestions(Integer.parseInt(totalQuestions));
			}
			sumQuestions += struct.getNumberQuestions();
			examStructures.add(struct);
		}
		
		FieldEntry field = new FieldEntry(Integer.parseInt(request.getParameter("fieldId")));
		
		ExamEntry exam = new ExamEntry(examId, examName, totalTime, sumQuestions, field, examStructures);
		
		boolean examError = ExamDAO.updateExam(exam);
		getServletContext().setAttribute("examError", examError);
		response.sendRedirect(request.getContextPath() + "/DanhSachDeThi");
	}

}
