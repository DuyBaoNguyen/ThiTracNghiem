package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ClassDAO;
import model.ClassEntry;

@WebServlet("/XoaLopHoc")
public class XoaLopHoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public XoaLopHoc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("classId");
		
		ClassEntry entry = new ClassEntry(classId);
		
		boolean classError = ClassDAO.deleteClass(entry);
		getServletContext().setAttribute("classError", classError);
		response.sendRedirect(request.getContextPath() + "/DanhSachLopHoc");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
