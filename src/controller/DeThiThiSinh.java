package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDAO;
import dao.ExamDAO;
import model.AccountEntry;
import model.CandidateEntry;
import model.ClassEntry;
import model.ExamEntry;

@WebServlet("/DanhSachLopHocThiSinh/LopHoc")
public class DeThiThiSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeThiThiSinh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountEntry account = (AccountEntry)request.getSession().getAttribute("account");
		CandidateEntry cdd = new CandidateEntry(account.getUser().getId());
		ClassEntry classEntry = new ClassEntry(request.getParameter("classId"));
		ClassDAO.getClass(classEntry);
		request.setAttribute("classEntry", classEntry);
		
		List<ExamEntry> notTakenExams = ExamDAO.getNotTakenExams(cdd, classEntry);
		request.setAttribute("exams", notTakenExams);
		
		List<ExamEntry> takenExams = ExamDAO.getTakenExams(cdd, classEntry);
		request.setAttribute("takenExams", takenExams);
		
		request.getRequestDispatcher("/WEB-INF/DeThiThiSinh.jsp").forward(request, response);
	}
}
