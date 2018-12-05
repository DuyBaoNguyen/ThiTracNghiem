package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDAO;
import dao.FieldDAO;
import model.ClassEntry;
import model.FieldEntry;

@WebServlet("/SuaLopHoc")
public class SuaLopHoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuaLopHoc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("classId");
		
		ClassEntry entry = ClassDAO.getClass(new ClassEntry(classId));
		List<FieldEntry> fields = FieldDAO.getFields();
		
		request.setAttribute("entry", entry);
		request.setAttribute("fields", fields);
		request.getRequestDispatcher("/WEB-INF/SuaLopHoc.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		int fieldId = Integer.parseInt(request.getParameter("fieldId"));
		
		ClassEntry entry = new ClassEntry(classId, className, new FieldEntry(fieldId));
		
		boolean classError = ClassDAO.updateClass(entry);
		getServletContext().setAttribute("classError", classError);
		response.sendRedirect(request.getContextPath() + "/DanhSachLopHoc");
	}

}
