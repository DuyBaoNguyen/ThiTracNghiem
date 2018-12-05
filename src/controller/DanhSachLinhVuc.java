package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FieldDAO;
import model.FieldEntry;

@WebServlet("/DanhSachLinhVuc")
public class DanhSachLinhVuc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DanhSachLinhVuc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchStr = request.getParameter("search");
		List<FieldEntry> fields = FieldDAO.getFields();
		fields = FieldDAO.searchFields(fields, searchStr);
		request.setAttribute("fields", fields);
		
		Boolean fieldError = (Boolean)getServletContext().getAttribute("fieldError");
		if (fieldError != null) {
			getServletContext().removeAttribute("fieldError");
		}
		else {
			fieldError = false;
		}
		request.setAttribute("fieldError", fieldError);
		
		
		request.getRequestDispatcher("/WEB-INF/DanhSachLinhVuc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
