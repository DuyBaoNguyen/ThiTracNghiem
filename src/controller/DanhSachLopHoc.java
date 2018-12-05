package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDAO;
import model.ClassEntry;

@WebServlet("/DanhSachLopHoc")
public class DanhSachLopHoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DanhSachLopHoc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ClassEntry> classes = ClassDAO.getClasses();
		String searchStr = request.getParameter("search");
		
		classes = ClassDAO.searchClasses(classes, searchStr);
		request.setAttribute("classes", classes);
		
		Boolean classError = (Boolean)getServletContext().getAttribute("classError");
		if (classError != null) {
			getServletContext().removeAttribute("classError");
		}
		else {
			classError = false;
		}
		request.setAttribute("classError", classError);
		
		request.getRequestDispatcher("/WEB-INF/DanhSachLopHoc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
