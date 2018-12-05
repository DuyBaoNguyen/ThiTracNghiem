package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FieldDAO;
import model.FieldEntry;

@WebServlet("/XoaLinhVuc")
public class XoaLinhVuc extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public XoaLinhVuc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fieldId = Integer.parseInt(request.getParameter("fieldId"));
		
		boolean fieldError = FieldDAO.deleteField(new FieldEntry(fieldId));
		getServletContext().setAttribute("fieldError", fieldError);
		
		response.sendRedirect(request.getContextPath() + "/DanhSachLinhVuc");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
