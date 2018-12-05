package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDAO;
import model.CandidateEntry;

@WebServlet("/XoaThiSinh")
public class XoaThiSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public XoaThiSinh() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cddId = request.getParameter("cddId");
		boolean cddError = CandidateDAO.deleteCandidate(new CandidateEntry(cddId));
		getServletContext().setAttribute("cddError", cddError);
		response.sendRedirect(request.getContextPath() + "/DanhSachThiSinh");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
