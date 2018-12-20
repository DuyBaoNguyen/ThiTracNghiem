package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamDAO;
import model.ClassEntry;
import model.ExamEntry;

@WebServlet("/BoGanDeThiChoLop")
public class BoGanDeThiChoLop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoGanDeThiChoLop() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("classId");
		int examId = Integer.parseInt(request.getParameter("examId"));
		boolean classExamError = ExamDAO.unassignExamforClass(new ClassEntry(classId), new ExamEntry(examId));
		getServletContext().setAttribute("classExamError", classExamError);
		
		response.sendRedirect(request.getContextPath() + "/DanhSachDeThi/DeThi?examId=" + examId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
