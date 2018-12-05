package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDAO;
import model.CandidateEntry;
import model.ClassEntry;

@WebServlet("/XoaThiSinhTrongLop")
public class XoaThiSinhTrongLop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public XoaThiSinhTrongLop() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("classId");
		String cddId = request.getParameter("cddId");
		
		boolean cddInClassError = CandidateDAO.deleteCddFromClass(new ClassEntry(classId), new CandidateEntry(cddId));
		getServletContext().setAttribute("cddInClassError", cddInClassError);
		
		response.sendRedirect(request.getContextPath() + "/DanhSachLopHoc/LopHoc?classId=" + classId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
