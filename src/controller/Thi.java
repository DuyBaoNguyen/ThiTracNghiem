package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerDAO;
import dao.ExamDAO;
import dao.QuestionDAO;
import dao.ResultDAO;
import model.AccountEntry;
import model.AnswerEntry;
import model.CandidateEntry;
import model.ClassEntry;
import model.ExamEntry;
import model.ExamStructureEntry;
import model.QuestionEntry;
import model.ResultDetailEntry;
import model.ResultEntry;

@WebServlet("/Thi")
public class Thi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Thi() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ExamEntry exam = new ExamEntry(Integer.parseInt(request.getParameter("examId")));
		ClassEntry classEntry = new ClassEntry(request.getParameter("classId"));
		
		AccountEntry account = (AccountEntry) request.getSession().getAttribute("account");
		CandidateEntry cdd = new CandidateEntry(account.getUser().getId(), account.getUser().getName());
		
		List<ExamEntry> takenExams = ExamDAO.getTakenExams(cdd, classEntry);
		
		for (ExamEntry item : takenExams) {
			if (item.getId() == exam.getId()) {
				response.sendRedirect(request.getContextPath() + "/KetQua?examId=" + exam.getId());
				return;
			}
		}
		
		List<ExamEntry> notTakenExams = ExamDAO.getNotTakenExams(cdd, classEntry);
		boolean hasExam = false;
		
		for (ExamEntry item : notTakenExams) {
			if (item.getId() == exam.getId()) {
				hasExam = true;
				break;
			}
		}
		
		if (hasExam == false) {
			response.sendRedirect(request.getContextPath() + "/KhongTheTruyCap");
			return;
		}
		
		ExamDAO.getExam(exam);
		request.setAttribute("exam", exam);

		List<QuestionEntry> questions = (List<QuestionEntry>) request.getSession().getAttribute("sessionQuests");
		if (questions == null) {
			Map<ExamStructureEntry, List<QuestionEntry>> mapQuestion = new HashMap<ExamStructureEntry, List<QuestionEntry>>();
			for (ExamStructureEntry item : exam.getExamStructures()) {
				mapQuestion.put(item, QuestionDAO.getQuestionsByFieldAndType(exam.getField(), item.getTypeQuestion()));
			}

			questions = new ArrayList<QuestionEntry>();
			for (ExamStructureEntry item : exam.getExamStructures()) {
				randomQuestions(questions, mapQuestion.get(item), item.getNumberQuestions());
			}
			for (QuestionEntry item : questions) {
				QuestionDAO.getQuestion(item);
			}
			request.getSession().setAttribute("sessionQuests", questions);
		}
		request.setAttribute("questions", questions);

		request.setAttribute("candidate", cdd);

		request.getRequestDispatcher("/WEB-INF/Thi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultEntry result = new ResultEntry();
		// Set exam for result
		ExamEntry exam = new ExamEntry(Integer.parseInt(request.getParameter("examId")));
		result.setExam(exam);

		// Set candidate for result
		AccountEntry account = (AccountEntry) request.getSession().getAttribute("account");
		CandidateEntry cdd = new CandidateEntry(account.getUser().getId());
		result.setCandidate(cdd);

		// Set detail of result for result
		List<ResultDetailEntry> resultDetails = new ArrayList<ResultDetailEntry>();
		int i = 0;
		int rightAnswers = 0;
		while (true) {
			String questionId = request.getParameter("quest" + i);
			if (questionId == null) {
				break;
			}
			QuestionEntry question = new QuestionEntry(Integer.parseInt(questionId));

			AnswerEntry answer = null;
			String answerId = request.getParameter("answer" + i);
			if (answerId != null) {
				answer = new AnswerEntry(Integer.parseInt(answerId));
			}

			ResultDetailEntry resultDetail = new ResultDetailEntry();
			resultDetail.setQuestion(question);
			resultDetail.setUserAnswer(answer);

			if (answer != null && AnswerDAO.getRightAnswerId(question) == answer.getId()) {
				resultDetail.setStatus(true);
				rightAnswers++;
			} else {
				resultDetail.setStatus(false);
			}
			resultDetail.setOrder(i);
			
			resultDetails.add(resultDetail);
			i++;
		}
		result.setResultDetails(resultDetails);
		
		result.setTotalQuestion(resultDetails.size());
		result.setTotalRightAnswers(rightAnswers);
		
		float score = Math.round((float)rightAnswers / result.getTotalQuestion() * 1000) / 100.0F;
		result.setScore(score);
		
		result.setTime(new Timestamp(System.currentTimeMillis()));

		ResultDAO.insertResult(result);
		request.getSession().removeAttribute("sessionQuests");
		response.sendRedirect(request.getContextPath() + "/KetQua?examId=" + exam.getId());
	}

	private void randomQuestions(List<QuestionEntry> result, List<QuestionEntry> input, int n) {
		int a, b = 0, length = input.size();
		double tmp = (double)input.size() / n;
		Random rand = new Random();

		if (tmp > 1 && tmp < 2 && (n - 1) * 2 >= length) {
			int count = 1;
			int tmp1 = n - 1;
			while (count * 2 + tmp1 != length) {
				count++;
				tmp1--;
			}
			for (int i = 0; i < n; i++) {
				if (i < count) {
					a = i * 2;
					b = (i + 1) * 2 - 1;
				} else {
					a = ++b;
				}
				
				result.add(input.get(rand.nextInt(b - a + 1) + a));
			}
		} else {
			int d = (int)Math.ceil(tmp);
			for (int i = 0; i < n; i++) {
				a = i * d;
				if (i == n - 1) {
					b = length - 1;
				} else {
					b = (i + 1) * d - 1;
				}

				result.add(input.get(rand.nextInt(b - a + 1) + a));
			}
		}
	}
}
