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

@WebServlet("/ThemCauHoi")
public class ThemCauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemCauHoi() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<TypeQuestionEntry> typeQuestions = TypeQuestionDAO.getTypeQuestions();
		request.setAttribute("typeQuestions", typeQuestions);

		List<FieldEntry> fields = FieldDAO.getFields();
		request.setAttribute("fields", fields);

		request.getRequestDispatcher("/WEB-INF/ThemCauHoi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String questionContent = request.getParameter("question");
		AnswerEntry rightAnswer = new AnswerEntry(request.getParameter("right-answer"));

		List<AnswerEntry> answers = new ArrayList<AnswerEntry>();
		answers.add(new AnswerEntry(request.getParameter("answer1")));
		answers.add(new AnswerEntry(request.getParameter("answer2")));
		answers.add(new AnswerEntry(request.getParameter("answer3")));
		answers.add(new AnswerEntry(request.getParameter("answer4")));

		FieldEntry field = new FieldEntry(Integer.parseInt(request.getParameter("fieldId")));
		TypeQuestionEntry typeQuestion = new TypeQuestionEntry(
				Integer.parseInt(request.getParameter("typeQuestionId")));

		QuestionEntry question = new QuestionEntry(questionContent, rightAnswer, answers, field, typeQuestion);
		
		boolean questionError = QuestionDAO.insertQuestion(question);
		getServletContext().setAttribute("questionError", questionError);
		response.sendRedirect(request.getContextPath() + "/DanhSachCauHoi");
	}

}
