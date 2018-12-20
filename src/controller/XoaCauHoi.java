package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDAO;
import model.QuestionEntry;

@WebServlet("/XoaCauHoi")
public class XoaCauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public XoaCauHoi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		
		boolean questionError = QuestionDAO.deleteQuestion(new QuestionEntry(questionId));
		getServletContext().setAttribute("questionError", questionError);
		response.sendRedirect(request.getContextPath() + "/DanhSachCauHoi");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
