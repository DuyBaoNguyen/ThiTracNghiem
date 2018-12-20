package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDAO;
import dao.ResultDAO;
import model.AccountEntry;
import model.CandidateEntry;
import model.ExamEntry;
import model.ResultEntry;

@WebServlet("/KetQua")
public class KetQua extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KetQua() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExamEntry exam = new ExamEntry(Integer.parseInt(request.getParameter("examId")));
		ExamDAO.getExam(exam);
		
		AccountEntry account = (AccountEntry)request.getSession().getAttribute("account");
		CandidateEntry cdd = new CandidateEntry(account.getUser().getId(), account.getUser().getName());
		
		ResultEntry result = new ResultEntry();
		result.setExam(exam);
		result.setCandidate(cdd);
		
		ResultDAO.getResult(result);
		request.setAttribute("result", result);
		
		request.getRequestDispatcher("/WEB-INF/KetQua.jsp").forward(request, response);
	}
}
