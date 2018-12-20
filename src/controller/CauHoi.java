package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FieldDAO;
import dao.QuestionDAO;
import dao.TypeQuestionDAO;
import model.AnswerEntry;
import model.FieldEntry;
import model.QuestionEntry;
import model.TypeQuestionEntry;

@WebServlet("/DanhSachCauHoi/CauHoi")
public class CauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CauHoi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		
		QuestionEntry question = QuestionDAO.getQuestion(new QuestionEntry(questionId));
		request.setAttribute("question", question);
		
		List<TypeQuestionEntry> typeQuestions = TypeQuestionDAO.getTypeQuestions();
		request.setAttribute("typeQuestions", typeQuestions);
		
		List<FieldEntry> fields = FieldDAO.getFields();
		request.setAttribute("fields", fields);
		
		request.getRequestDispatcher("/WEB-INF/CauHoi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		String questionContent = request.getParameter("questionContent");
		
		int i = 0;
		List<AnswerEntry> answers = new ArrayList<AnswerEntry>();
		while (true) {
			String answerId = request.getParameter("answerId" + i);
			if (answerId != null) {
				answers.add(new AnswerEntry(Integer.parseInt(answerId), request.getParameter("answerContent" + i)));
				i++;
				continue;
			}
			break;
		}
		
		AnswerEntry rightAnswer = new AnswerEntry(Integer.parseInt(request.getParameter("right-answer")));
		FieldEntry field = new FieldEntry(Integer.parseInt(request.getParameter("fieldId")));
		TypeQuestionEntry typeQuestion = new TypeQuestionEntry(Integer.parseInt(request.getParameter("typeQuestionId")));
		QuestionEntry question = new QuestionEntry(questionId, questionContent, field, typeQuestion, rightAnswer, answers);
		
		boolean questionError = QuestionDAO.updateQuestion(question);
		getServletContext().setAttribute("questionError", questionError);
		response.sendRedirect(request.getContextPath() + "/DanhSachCauHoi");
	}
}
