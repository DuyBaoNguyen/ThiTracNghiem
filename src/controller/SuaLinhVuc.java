package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FieldDAO;
import model.FieldEntry;

@WebServlet("/SuaLinhVuc")
public class SuaLinhVuc extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public SuaLinhVuc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fieldId = Integer.parseInt(request.getParameter("fieldId"));
		
		FieldEntry field = FieldDAO.getField(new FieldEntry(fieldId));
		request.setAttribute("field", field);
		
		request.getRequestDispatcher("/WEB-INF/SuaLinhVuc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int fieldId = Integer.parseInt(request.getParameter("fieldId"));
		String fieldName = request.getParameter("fieldName");
		
		boolean fieldError = FieldDAO.updateField(new FieldEntry(fieldId, fieldName));
		getServletContext().setAttribute("fieldError", fieldError);
		
		response.sendRedirect(request.getContextPath() + "/DanhSachLinhVuc");
	}

}
