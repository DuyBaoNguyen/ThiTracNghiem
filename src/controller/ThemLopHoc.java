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

@WebServlet("/ThemLopHoc")
public class ThemLopHoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ThemLopHoc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FieldEntry> fields = FieldDAO.getFields();
		request.setAttribute("fields", fields);
		
		request.getRequestDispatcher("/WEB-INF/ThemLopHoc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String className = request.getParameter("className");
		int fieldId = Integer.parseInt(request.getParameter("fieldId"));
		
		ClassEntry entry = new ClassEntry(className, new FieldEntry(fieldId));
		
		boolean classError = ClassDAO.insertClass(entry);
		getServletContext().setAttribute("classError", classError);
		response.sendRedirect(request.getContextPath() + "/DanhSachLopHoc");
	}

}
