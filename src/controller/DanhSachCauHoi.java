package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDAO;
import model.QuestionEntry;

@WebServlet("/DanhSachCauHoi")
public class DanhSachCauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DanhSachCauHoi() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<QuestionEntry> questions = QuestionDAO.getQuestions();
		String searchStr = request.getParameter("search");
		
		questions = QuestionDAO.searchQuestions(questions, searchStr);
		request.setAttribute("questions", questions);
		
		Boolean questionError = (Boolean)getServletContext().getAttribute("questionError");
		if (questionError != null) {
			getServletContext().removeAttribute("questionError");
		}
		else {
			questionError = false;
		}
		request.setAttribute("questionError", questionError);
		
		Boolean insertQuestionsError = (Boolean)getServletContext().getAttribute("insertQuestionsError");
		if (insertQuestionsError != null) {
			getServletContext().removeAttribute("insertQuestionsError");
		}
		else {
			insertQuestionsError = false;
		}
		request.setAttribute("insertQuestionsError", insertQuestionsError);
		
		request.getRequestDispatcher("/WEB-INF/DanhSachCauHoi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
