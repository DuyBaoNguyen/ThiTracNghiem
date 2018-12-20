package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDAO;
import model.ExamEntry;

@WebServlet("/XoaDeThi")
public class XoaDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public XoaDeThi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int examId = Integer.parseInt(request.getParameter("examId"));
		boolean examError = ExamDAO.deleteExam(new ExamEntry(examId));
		getServletContext().setAttribute("examError", examError);
		response.sendRedirect(request.getContextPath() + "/DanhSachDeThi");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
